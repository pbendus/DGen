package doc_utils;

import db.entities.ClassificationSystem;
import db.entities.ClassificationSystemConst;
import db.entities.Diploma;
import db.entities.EducationalComponent;
import db.services.ClassificationSystemService;
import db.services.DiplomaService;
import db.services.EducationalComponentService;
import db.services.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ui.models.StudentWithAVG;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DocWorker {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String DIRECTORY_PATH = "documents/";
    private static final String DOCX = ".docx";
    private static final String STUDENT_RATING = "student_rating";

    @Value("${doc.pattern}")
    private String key;

    @Value("${doc.diploma}")
    private String diploma;
    @Value("${doc.diploma_en}")
    private String diplomaEn;
    @Value("${doc.diploma_honor}")
    private String diplomaHonor;
    @Value("${doc.diploma_en_honor}")
    private String diplomaEnHonor;

    @Value("${doc.information_on_certification}")
    private String informationOnCertification;
    @Value("${doc.information_on_certification_en}")
    private String informationOnCertificationEn;

    private XWPFDocument document;

    private EducationalComponentService educationalComponentService;
    private DiplomaService diplomaService;
    private StudentService studentService;
    private ClassificationSystemService classificationSystemService;
    private AppProperties appProperties;

    @Autowired
    public DocWorker(EducationalComponentService educationalComponentService,
                     DiplomaService diplomaService, StudentService studentService, ClassificationSystemService classificationSystemService, AppProperties appProperties) {
        this.educationalComponentService = educationalComponentService;
        this.diplomaService = diplomaService;
        this.studentService = studentService;
        this.classificationSystemService = classificationSystemService;
        this.appProperties = appProperties;
    }

    private boolean isVariable(String string) {
        if (string == null || string.length() < 4) {
            return false;
        }
        final String regex = ".*" + key + ".*" + key + ".*";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    private Map<DocVariableConst, DocVariable> findAllVariables() {
        final Map<DocVariableConst, DocVariable> docVariables = new HashMap<>();

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            final List<XWPFRun> runs = paragraph.getRuns();
            if (runs != null) {
                final String text = paragraph.getText();
                if (isVariable(text)) {
                    for (DocVariableConst docVariableConst :
                            DocVariableConst.values()) {
                        final String variable = text.replaceAll(key, "");
                        if (docVariableConst.getValue().equals(variable)) {
                            docVariables.put(docVariableConst,
                                    new DocVariable(text, paragraph, docVariableConst));
                            LOGGER.info(String.format("Variable {%s} has been found", text));
                        }
                    }
                }
            }
        }

        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                docVariables.putAll(findAllVariables(row));
            }
        }

        return docVariables;
    }

    private String saveDocument(String fileName, int studentId) throws IOException, SQLException {
        final String groupName = studentService.getGroupByStudentId(studentId).getName() + "/";
        final String directoryName = DIRECTORY_PATH.concat(groupName);
        final File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        final String path = directoryName + fileName.trim() + DOCX;
        document.write(new FileOutputStream(path));
        LOGGER.info(String.format("Document %s has been created", fileName));

        return path;
    }

    private String saveDocumentRating(int id) throws IOException {
        final String directoryName = DIRECTORY_PATH;
        final File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        final String path = directoryName + STUDENT_RATING + (id == 2 ? "(z)" : "") + DOCX;
        document.write(new FileOutputStream(path));
        LOGGER.info(String.format("Document %s has been created", path));

        return path;
    }

    public String generateDocument(int studentId, String documentName)
            throws IOException, XmlException, SQLException {
        document = getInputDocument();

        final Map<DocVariableConst, DocVariable> variables = findAllVariables();
        final Diploma diploma = diplomaService.getByStudentId(studentId);

        for (DocVariable docVariable :
                variables.values()) {
            changeParagraph(docVariable, diploma);
        }

        addCourses(educationalComponentService.getAllCoursesByDiplomaId(diploma.getId()), variables);
        addInternships(educationalComponentService.getAllInternshipsByDiplomaId(diploma.getId()),
                variables);
        addResearchProjects(
                educationalComponentService.getAllResearchProjectsByDiplomaId(diploma.getId()), variables);
        addStateAttestations(
                educationalComponentService.getAllStateAttestationsByDiplomaId(diploma.getId()), variables);

        return saveDocument(documentName, studentId);
    }

    private void addCourses(List<EducationalComponent> components,
                            Map<DocVariableConst, DocVariable> variables)
            throws IOException, XmlException {
        for (int i = 0; i < components.size(); i++) {
            if (components.indexOf(components.get(i)) != components.size() - 1) {
                final XWPFTableRow row =
                        variables.get(DocVariableConst.COMPONENT_N).getCell().getTableRow();
                final XWPFTable table = row.getTable();

                final CTRow ctrow = CTRow.Factory.parse(row.getCtRow().newInputStream());

                final XWPFTableRow newRow = new XWPFTableRow(ctrow, table);
                final Map<DocVariableConst, DocVariable> docVariables = findAllVariables(newRow);
                for (DocVariable docVariable :
                        docVariables.values()) {
                    changeCourseData(docVariable, components.get(i), i + 1);
                }
                table.addRow(newRow, table.getRows().indexOf(row));
            } else {
                for (DocVariable docVariable :
                        variables.values()) {
                    changeCourseData(docVariable, components.get(i), i + 1);
                }
            }
        }
    }

    private void addResearchProjects(List<EducationalComponent> components,
                                     Map<DocVariableConst, DocVariable> variables) throws IOException, XmlException {
        for (int i = 0; i < components.size(); i++) {
            if (components.indexOf(components.get(i)) != components.size() - 1) {
                final XWPFTableRow row =
                        variables.get(DocVariableConst.RESEARCH_N).getCell().getTableRow();
                final XWPFTable table = row.getTable();

                final CTRow ctrow = CTRow.Factory.parse(row.getCtRow().newInputStream());

                final XWPFTableRow newRow = new XWPFTableRow(ctrow, table);
                final Map<DocVariableConst, DocVariable> docVariables = findAllVariables(newRow);
                for (DocVariable docVariable :
                        docVariables.values()) {
                    changeResearchData(docVariable, components.get(i), i + 1);
                }
                table.addRow(newRow, table.getRows().indexOf(row));
            } else {
                for (DocVariable docVariable :
                        variables.values()) {
                    changeResearchData(docVariable, components.get(i), i + 1);
                }
            }
        }
    }

    private void addInternships(List<EducationalComponent> components,
                                Map<DocVariableConst, DocVariable> variables)
            throws IOException, XmlException {
        for (int i = 0; i < components.size(); i++) {
            if (components.indexOf(components.get(i)) != components.size() - 1) {
                final XWPFTableRow row =
                        variables.get(DocVariableConst.INTERNSHIP_N).getCell().getTableRow();
                final XWPFTable table = row.getTable();

                final CTRow ctrow = CTRow.Factory.parse(row.getCtRow().newInputStream());

                final XWPFTableRow newRow = new XWPFTableRow(ctrow, table);
                final Map<DocVariableConst, DocVariable> docVariables = findAllVariables(newRow);
                for (DocVariable docVariable :
                        docVariables.values()) {
                    changeInternshipData(docVariable, components.get(i), i + 1);
                }
                table.addRow(newRow, table.getRows().indexOf(row));
            } else {
                for (DocVariable docVariable :
                        variables.values()) {
                    changeInternshipData(docVariable, components.get(i), i + 1);
                }
            }
        }
    }

    private void addStateAttestations(List<EducationalComponent> components,
                                      Map<DocVariableConst, DocVariable> variables) throws IOException, XmlException {
        for (int i = 0; i < components.size(); i++) {
            if (components.indexOf(components.get(i)) != components.size() - 1) {
                final XWPFTableRow row =
                        variables.get(DocVariableConst.ATTESTATION_N).getCell().getTableRow();
                final XWPFTable table = row.getTable();

                final CTRow ctrow = CTRow.Factory.parse(row.getCtRow().newInputStream());

                final XWPFTableRow newRow = new XWPFTableRow(ctrow, table);
                final Map<DocVariableConst, DocVariable> docVariables = findAllVariables(newRow);
                for (DocVariable docVariable :
                        docVariables.values()) {
                    changeAttestationData(docVariable, components.get(i), i + 1);
                }
                table.addRow(newRow, table.getRows().indexOf(row));
            } else {
                for (DocVariable docVariable :
                        variables.values()) {
                    changeAttestationData(docVariable, components.get(i), i + 1);
                }
            }
        }
    }

    private void changeCourseData(DocVariable docVariable,
                                  EducationalComponent component, int n) {
        switch (docVariable.getDocVariableConst()) {
            case COMPONENT_N:
                changeParagraph(docVariable.getParagraph(), String.valueOf(n), false);
                break;
            case COMPONENT_TITLE:
                if (component.getNationalScore() < 60) {
                    changeParagraph(docVariable.getParagraph(),
                            component.getEducationalComponentTemplate().getCourseTitle(), true, "d23434");
                } else {
                    changeParagraph(docVariable.getParagraph(),
                            component.getEducationalComponentTemplate().getCourseTitle(), true);
                }
                break;
            case COMPONENT_CREDITS:
                double value = component.getEducationalComponentTemplate().getCredits();
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf((value - Math.floor(value) == 0) ? "" + ((int) value) : value), false);
                break;
            case COMPONENT_SCORE:
                changeParagraph(docVariable.getParagraph(), String.valueOf(component.getNationalScore()),
                        false);
                break;
            case COMPONENT_RATING_POINT:
                changeParagraph(docVariable.getParagraph(), component.getRatingPoint().getName(), false);
                break;
            case COMPONENT_NATIONAL_GRADE:
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf(component.getNationalGrade().getName()), true);
                break;
        }
    }

    private void changeResearchData(DocVariable docVariable,
                                    EducationalComponent component, int n) {
        switch (docVariable.getDocVariableConst()) {
            case RESEARCH_N:
                changeParagraph(docVariable.getParagraph(), String.valueOf(n), false);
                break;
            case RESEARCH_TITLE:
                if (component.getNationalScore() < 60) {
                    changeParagraph(docVariable.getParagraph(),
                            component.getEducationalComponentTemplate().getCourseTitle(), true, "d23434");
                } else {
                    changeParagraph(docVariable.getParagraph(),
                            component.getEducationalComponentTemplate().getCourseTitle(), true);
                }
                break;
            case RESEARCH_CREDITS:
                double value = component.getEducationalComponentTemplate().getCredits();
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf((value - Math.floor(value) == 0) ? "" + ((int) value) : value), true);
                break;
            case RESEARCH_SCORE:
                changeParagraph(docVariable.getParagraph(), String.valueOf(component.getNationalScore()),
                        false);
                break;
            case RESEARCH_RATING_POINT:
                changeParagraph(docVariable.getParagraph(), component.getRatingPoint().getName(), true);
                break;
            case RESEARCH_NATIONAL_GRADE:
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf(component.getNationalGrade().getName()), true);
                break;
        }
    }

    private void changeInternshipData(DocVariable docVariable,
                                      EducationalComponent component, int n) {
        switch (docVariable.getDocVariableConst()) {
            case INTERNSHIP_N:
                changeParagraph(docVariable.getParagraph(), String.valueOf(n), false);
                break;
            case INTERNSHIP_TITLE:
                if (component.getNationalScore() < 60) {
                    changeParagraph(docVariable.getParagraph(),
                            component.getEducationalComponentTemplate().getCourseTitle(), true, "d23434");
                } else {
                    changeParagraph(docVariable.getParagraph(),
                            component.getEducationalComponentTemplate().getCourseTitle(), true);
                }
                break;
            case INTERNSHIP_CREDITS:
                double value = component.getEducationalComponentTemplate().getCredits();
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf((value - Math.floor(value) == 0) ? "" + ((int) value) : value), true);
                break;
            case INTERNSHIP_SCORE:
                changeParagraph(docVariable.getParagraph(), String.valueOf(component.getNationalScore()),
                        false);
                break;
            case INTERNSHIP_RATING_POINT:
                changeParagraph(docVariable.getParagraph(), component.getRatingPoint().getName(), true);
                break;
            case INTERNSHIP_NATIONAL_GRADE:
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf(component.getNationalGrade().getName()), true);
                break;
        }
    }

    private void changeAttestationData(DocVariable docVariable,
                                       EducationalComponent component, int n) {
        switch (docVariable.getDocVariableConst()) {
            case ATTESTATION_N:
                changeParagraph(docVariable.getParagraph(), String.valueOf(n), false);
                break;
            case ATTESTATION_TITLE:
                if (component.getNationalScore() < 60) {
                    changeParagraph(docVariable.getParagraph(),
                            component.getEducationalComponentTemplate().getCourseTitle(), true, "d23434");
                } else {
                    changeParagraph(docVariable.getParagraph(),
                            component.getEducationalComponentTemplate().getCourseTitle(), true);
                }
                break;
            case ATTESTATION_CREDITS:
                double value = component.getEducationalComponentTemplate().getCredits();
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf((value - Math.floor(value) == 0) ? "" + ((int) value) : value), true);
                break;
            case ATTESTATION_SCORE:
                changeParagraph(docVariable.getParagraph(), String.valueOf(component.getNationalScore()),
                        false);
                break;
            case ATTESTATION_RATING_POINT:
                changeParagraph(docVariable.getParagraph(), component.getRatingPoint().getName(), true);
                break;
            case ATTESTATION_NATIONAL_GRADE:
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf(component.getNationalGrade().getName()), true);
                break;
        }
    }

    private Map<DocVariableConst, DocVariable> findAllVariables(XWPFTableRow row) {
        final Map<DocVariableConst, DocVariable> variables = new HashMap<>();

        for (XWPFTableCell cell : row.getTableCells()) {
            for (XWPFParagraph paragraph : cell.getParagraphs()) {
                final String text = paragraph.getText();
                if (isVariable(text)) {
                    for (DocVariableConst docVariableConst :
                            DocVariableConst.values()) {
                        final String variable = text.replaceAll(key, "");
                        if (docVariableConst.getValue().equals(variable)) {
                            variables.put(docVariableConst,
                                    new DocVariable(text, paragraph, cell, docVariableConst));
                            LOGGER.info(String.format("Variable {%s} has been found", text));
                        }
                    }
                }
            }
        }

        return variables;
    }

    private XWPFDocument getInputDocument() throws IOException {
        FileInputStream fis = new FileInputStream(new File(appProperties.getInputFilePath()));

        return new XWPFDocument(fis);
    }

    private XWPFDocument getStudentRatingDocument() throws IOException {
        FileInputStream fis = new FileInputStream(new File(appProperties.getStudentRatingInputPath()));

        return new XWPFDocument(fis);
    }

    public String generateRatingDocument(List<StudentWithAVG> studentWithAVGS) throws IOException,
            XmlException, SQLException {
        document = getStudentRatingDocument();

        final Map<DocVariableConst, DocVariable> variables = findAllVariables();

        addStudentRatingRows(studentWithAVGS, variables);

        return saveDocumentRating(studentWithAVGS.get(0).getDiploma().getStudent().getModeOfStudy().getId());
    }

    private void addStudentRatingRows(List<StudentWithAVG> components,
                                      Map<DocVariableConst, DocVariable> variables)
            throws IOException, XmlException, SQLException {
        for (int i = 0; i < components.size(); i++) {
            if (components.indexOf(components.get(i)) != components.size() - 1) {
                final XWPFTableRow row =
                        variables.get(DocVariableConst.STUDENT_RATING_ID).getCell().getTableRow();
                final XWPFTable table = row.getTable();

                final CTRow ctrow = CTRow.Factory.parse(row.getCtRow().newInputStream());

                final XWPFTableRow newRow = new XWPFTableRow(ctrow, table);
                final Map<DocVariableConst, DocVariable> docVariables = findAllVariables(newRow);
                for (DocVariable docVariable :
                        docVariables.values()) {
                    changeStudentRatingData(docVariable, components.get(i), i + 1);
                }
                table.addRow(newRow, table.getRows().indexOf(row));
            } else {
                for (DocVariable docVariable :
                        variables.values()) {
                    changeStudentRatingData(docVariable, components.get(i), i + 1);
                }
            }
        }
    }

    private void changeStudentRatingData(DocVariable docVariable, StudentWithAVG studentWithAVG, int i) throws SQLException {
        switch (docVariable.getDocVariableConst()) {
            case STUDENT_RATING_ID:
                changeParagraph(docVariable.getParagraph(), false, String.valueOf(i));
                break;
            case STUDENT_RATING_NAME:
                boolean isDiplomaWithHonor = educationalComponentService.isDiplomaWithHonor(studentWithAVG.getDiploma().getId());
                changeParagraph(docVariable.getParagraph(), isDiplomaWithHonor,
                        studentWithAVG.getDiploma().getStudent().fullNameProperty().get());
                break;
            case STUDENT_RATING_AVG:
                changeParagraph(docVariable.getParagraph(), true,
                        String.valueOf(studentWithAVG.getAvg()));
                break;
            case STUDENT_RATING_FIVE:
                changeParagraph(docVariable.getParagraph(), false,
                        String.valueOf(studentWithAVG.getNumberOfFive()));
                break;
            case STUDENT_RATING_FOUR:
                changeParagraph(docVariable.getParagraph(), false,
                        String.valueOf(studentWithAVG.getNumberOfFour()));
                break;
            case STUDENT_RATING_THREE:
                changeParagraph(docVariable.getParagraph(), false,
                        String.valueOf(studentWithAVG.getNumberOfThree()));
                break;
            case STUDENT_RATING_TOTAL:
                changeParagraph(docVariable.getParagraph(), false,
                        String.valueOf(studentWithAVG.getNumberOfEducationalComponent()));
                break;
        }
    }

    private void changeParagraph(DocVariable docVariable, Diploma diploma) throws SQLException {
        ClassificationSystem classificationSystem = classificationSystemService.getByName(educationalComponentService.isDiplomaWithHonor(diploma.getId()) ?
                ClassificationSystemConst.DIPLOMA_WITH_HONORS : ClassificationSystemConst.DIPLOMA);
        switch (docVariable.getDocVariableConst()) {
            case DIPLOMA:
                changeParagraph(docVariable.getParagraph(), diploma.getNumber(), false);
                break;
            case REGISTRATION_NUMBER:
                changeParagraph(docVariable.getParagraph(), diploma.getRegistrationNumber(), false);
                break;
            case ADD_REGISTRATION_NUMBER:
                changeParagraph(docVariable.getParagraph(), diploma.getAdditionRegistrationNumber(), false);
                break;
            case DATE_OF_ISSUE:
                changeParagraph(docVariable.getParagraph(),
                        new SimpleDateFormat("dd.MM.yyyy").format(diploma.getDateOfIssue()), false);
                break;
            case DATE_OF_ISSUE_ADD:
                changeParagraph(docVariable.getParagraph(),
                        new SimpleDateFormat("dd.MM.yyyy").format(diploma.getDateOfIssue()), false);
                break;
            case FAMILY_NAME:
                changeParagraph(docVariable.getParagraph(), diploma.getStudent().getFamilyName(), false);
                break;
            case GIVEN_NAME:
                changeParagraph(docVariable.getParagraph(), diploma.getStudent().getGivenName(), false);
                break;
            case FAMILY_NAME_TR:
                changeParagraph(docVariable.getParagraph(), diploma.getStudent().getFamilyNameTr(), false);
                break;
            case GIVEN_NAME_TR:
                changeParagraph(docVariable.getParagraph(), diploma.getStudent().getGivenNameTr(), false);
                break;
            case DATE_OF_BIRTH:
                changeParagraph(docVariable.getParagraph(),
                        new SimpleDateFormat("dd/MM/yyyy").format(diploma.getStudent().getDateOfBirth()),
                        false);
                break;
            case MAIN_FIELD:
                changeParagraph(docVariable.getParagraph(), diploma.getMainField().getName(), true);
                break;
            case FIELD_OF_STUDY:
                changeParagraph(docVariable.getParagraph(), diploma.getFieldOfStudy().getName(), true);
                break;
            case OFFICIAL_DURATION_OF_PROGRAMME:
                changeParagraph(docVariable.getParagraph(),
                        diploma.getOfficialDurationOfProgramme().getName(), true);
                break;
            case ACCESS_REQUIREMENTS:
                changeParagraph(docVariable.getParagraph(), diploma.getAccessRequirements().getName(),
                        true);
                break;
            case MODE_OF_STUDY:
                changeParagraph(docVariable.getParagraph(),
                        diploma.getDurationOfTraining().getModeOfStudy().getName(), true);
                break;
            case CREDITS_GAINED:
                double value = educationalComponentService.getCreditsGained(diploma.getId());
                changeParagraph(docVariable.getParagraph(),
                        String.valueOf((value - Math.floor(value) == 0) ? "" + ((int) value) : value),
                        false);
                break;
            case CLASSIFICATION_SYSTEM:
                changeParagraph(docVariable.getParagraph(), classificationSystem.getName(), true);
                break;
            case CLASSIFICATION_SYSTEM_DESCRIPTION:
                String criteria = classificationSystem.getCriteria();
                changeParagraph(docVariable.getParagraph(),
                        criteria, true);
                break;
            case DURATION_OF_TRAINING:
                changeParagraph(docVariable.getParagraph(), diploma.getDurationOfTraining().getName(),
                        true);
                break;
            case INFORMATION_ON_CERTIFICATION:
                changeParagraph(docVariable.getParagraph(), diploma.getInformationOnCertification(informationOnCertification, informationOnCertificationEn), true);
                break;
            case PREVIOUS_DOCUMENT:
                String previousDocument = diploma.getStudent().getPreviousDocument().getName();
                if (!diploma.getStudent().getPreviousDocument().getNameEN().isEmpty()) {
                    previousDocument = previousDocument + " / " +
                            diploma.getStudent().getPreviousDocument().getNameEN();
                }

                changeParagraph(docVariable.getParagraph(), previousDocument, true);
                break;
            case ECTS_CREDITS:
                changeParagraph(docVariable.getParagraph(),
                        diploma.getEctsCredits().getName(), true);
                break;
            case DATE:
                changeParagraph(docVariable.getParagraph(),
                        new SimpleDateFormat("dd/MM/yyyy").format(diploma.getDateOfIssue()), false);
                break;

            case D_TITLE:
                final String name = classificationSystem.getName().split("/")[0];
                changeParagraph(docVariable.getParagraph(), name, false);
                break;
            case D_TITLE_EN:
                final String nameEn = classificationSystem.getName().split("/")[1];
                changeParagraph(docVariable.getParagraph(), nameEn, false);
                break;

            case D_HONOURS:
                final String title = educationalComponentService.isDiplomaWithHonor(diploma.getId()) ?
                        this.diplomaHonor : this.diploma;
                changeParagraph(docVariable.getParagraph(), title, false);
                break;
            case D_HONOURS_EN:
                final String title_en = educationalComponentService.isDiplomaWithHonor(diploma.getId()) ?
                        diplomaEnHonor : diplomaEn;
                changeParagraph(docVariable.getParagraph(), title_en, false);
                break;

            case DIPLOMA_TITLE:
                changeParagraph(docVariable.getParagraph(), classificationSystem.getName().split("/")[0], false);
                break;
            case DIPLOMA_TITLE_EN:
                String diplomaTitleEn = classificationSystem.getName().split("/ ")[1];
                diplomaTitleEn = diplomaTitleEn.substring(0, 1).trim().toUpperCase() + diplomaTitleEn.substring(1).toLowerCase();
                changeParagraph(docVariable.getParagraph(), diplomaTitleEn, false);
                break;
        }
    }

    private void changeParagraph(XWPFParagraph paragraph, String value, boolean splitAndSetItalic) {
        paragraph.getRuns().forEach(xwpfRun -> xwpfRun.setText("", 0));
        if (splitAndSetItalic) {
            final String[] s = value.split("/");
            if (s.length == 2 && paragraph.getRuns().size() > 1) {
                paragraph.getRuns().get(0).setText(s[0] + " /", 0);
                paragraph.getRuns().get(1).setText(s[1], 0);
                paragraph.getRuns().get(1).setItalic(true);
            } else {
                paragraph.getRuns().get(0).setText(value, 0);
            }
        } else {
            paragraph.getRuns().get(0).setText(value, 0);
        }

        LOGGER.info(String.format("Paragraph{%s} has been changed, value(%s)", paragraph, value));
    }

    private void changeParagraph(XWPFParagraph paragraph, String value, boolean splitAndSetItalic, String color) {
        paragraph.getRuns().forEach(xwpfRun -> xwpfRun.setText("", 0));
        if (splitAndSetItalic) {
            final String[] s = value.split("/");
            if (s.length == 2 && paragraph.getRuns().size() > 1) {
                paragraph.getRuns().get(0).setColor(color);
                paragraph.getRuns().get(0).setText(s[0] + " /", 0);
                paragraph.getRuns().get(1).setColor(color);
                paragraph.getRuns().get(1).setText(s[1], 0);
                paragraph.getRuns().get(1).setItalic(true);
            } else {
                paragraph.getRuns().get(0).setColor(color);
                paragraph.getRuns().get(0).setText(s[1], 0);
                paragraph.getRuns().get(0).setItalic(true);
                paragraph.getRuns().get(0).setText(value, 0);
            }
        } else {
            paragraph.getRuns().get(0).setText(value, 0);
        }

        LOGGER.info(String.format("Paragraph{%s} has been changed, value(%s)", paragraph, value));
    }

    private void changeParagraph(XWPFParagraph paragraph, boolean bold, String value) {
        paragraph.getRuns().forEach(xwpfRun -> xwpfRun.setText("", 0));
        paragraph.getRuns().get(0).setText(value, 0);
        paragraph.getRuns().get(0).setBold(bold);
        LOGGER.info(String.format("Paragraph{%s} has been changed, value(%s)", paragraph, value));
    }

    public void openFile(String file) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(new File(file));
        }
    }
}
