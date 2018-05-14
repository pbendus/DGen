package doc_utils;

import db.entities.Diploma;
import db.entities.EducationalComponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocWorker {
  @Value("${doc.pattern}")
  private String key;
  @Value("${doc.inputFilePath}")
  private String inputFilePath;

  private XWPFDocument document;

  public boolean isVariable(String string) {
    if (string == null || string.length() < 4) {
      return false;
    }
    final String regex = ".*" + key + ".*" + key + ".*";

    final Pattern pattern = Pattern.compile(regex);
    final Matcher matcher = pattern.matcher(string);

    return matcher.find();
  }

  public Map<DocVariableConst, DocVariable> findAllVariables() {
    final Map<DocVariableConst, DocVariable> docVariables = new HashMap<>();

    for (XWPFParagraph paragraph : document.getParagraphs()) {
      final List<XWPFRun> runs = paragraph.getRuns();
      if (runs != null) {
        final String text = paragraph.getText();
        if (text != null && isVariable(text)) {
          for (DocVariableConst docVariableConst :
              DocVariableConst.values()) {
            final String variable = text.replaceAll(key, "");
            if (docVariableConst.getValue().equals(variable)) {
              docVariables.put(docVariableConst,
                  new DocVariable(text, paragraph, docVariableConst));
              System.out.println(text);
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

  public void saveDocument(String path) throws IOException {
    document.write(new FileOutputStream(path));
  }

  public void generateDocument(Diploma diploma) throws IOException, XmlException {
    document = getInputDocument();

    final Map<DocVariableConst, DocVariable> variables = findAllVariables();

    for (DocVariable docVariable :
        variables.values()) {
      changeParagraph(docVariable, diploma);
    }

    addCourses(diploma.getAllCourses(), variables);
    addInternships(diploma.getAllInternships(), variables);
    addResearchProjects(diploma.getAllResearchProjects(), variables);
    addStateAttestations(diploma.getAllStateAttestations(), variables);

    saveDocument("output.docx");
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
        changeParagraph(docVariable.getParagraph(), component.getCourseTitle(), true);
        break;
      case COMPONENT_CREDITS:
        changeParagraph(docVariable.getParagraph(), String.valueOf(component.getCredit()), false);
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
            String.valueOf(component.getNationalGrade().getName()), false);
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
        changeParagraph(docVariable.getParagraph(), component.getCourseTitle(), false);
        break;
      case RESEARCH_CREDITS:
        changeParagraph(docVariable.getParagraph(), String.valueOf(component.getCredit()), false);
        break;
      case RESEARCH_SCORE:
        changeParagraph(docVariable.getParagraph(), String.valueOf(component.getNationalScore()),
            false);
        break;
      case RESEARCH_RATING_POINT:
        changeParagraph(docVariable.getParagraph(), component.getRatingPoint().getName(), false);
        break;
      case RESEARCH_NATIONAL_GRADE:
        changeParagraph(docVariable.getParagraph(),
            String.valueOf(component.getNationalGrade().getName()), false);
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
        changeParagraph(docVariable.getParagraph(), component.getCourseTitle(), false);
        break;
      case INTERNSHIP_CREDITS:
        changeParagraph(docVariable.getParagraph(), String.valueOf(component.getCredit()), false);
        break;
      case INTERNSHIP_SCORE:
        changeParagraph(docVariable.getParagraph(), String.valueOf(component.getNationalScore()),
            false);
        break;
      case INTERNSHIP_RATING_POINT:
        changeParagraph(docVariable.getParagraph(), component.getRatingPoint().getName(), false);
        break;
      case INTERNSHIP_NATIONAL_GRADE:
        changeParagraph(docVariable.getParagraph(),
            String.valueOf(component.getNationalGrade().getName()), false);
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
        changeParagraph(docVariable.getParagraph(), component.getCourseTitle(), false);
        break;
      case ATTESTATION_CREDITS:
        changeParagraph(docVariable.getParagraph(), String.valueOf(component.getCredit()), false);
        break;
      case ATTESTATION_SCORE:
        changeParagraph(docVariable.getParagraph(), String.valueOf(component.getNationalScore()),
            false);
        break;
      case ATTESTATION_RATING_POINT:
        changeParagraph(docVariable.getParagraph(), component.getRatingPoint().getName(), false);
        break;
      case ATTESTATION_NATIONAL_GRADE:
        changeParagraph(docVariable.getParagraph(),
            String.valueOf(component.getNationalGrade().getName()), false);
        break;
    }
  }

  private Map<DocVariableConst, DocVariable> findAllVariables(XWPFTableRow row) {
    final Map<DocVariableConst, DocVariable> variables = new HashMap<>();

    for (XWPFTableCell cell : row.getTableCells()) {
      for (XWPFParagraph paragraph : cell.getParagraphs()) {
        final String text = paragraph.getText();
        if (text != null && isVariable(text)) {
          for (DocVariableConst docVariableConst :
              DocVariableConst.values()) {
            final String variable = text.replaceAll(key, "");
            if (docVariableConst.getValue().equals(variable)) {
              System.out.println(text);
              variables.put(docVariableConst,
                  new DocVariable(text, paragraph, cell, docVariableConst));
            }
          }
        }
      }
    }

    return variables;
  }

  private XWPFDocument getInputDocument() throws IOException {
    FileInputStream fis = new FileInputStream(new File(inputFilePath));

    return new XWPFDocument(fis);
  }

  private void changeParagraph(DocVariable docVariable, Diploma diploma) {
    switch (docVariable.getDocVariableConst()) {
      case DIPLOMA:
        changeParagraph(docVariable.getParagraph(), diploma.getNumber(), false);
        break;
      case REGISTRATION_NUMBER:
        changeParagraph(docVariable.getParagraph(), diploma.getRegistrationNumber(), false);
        break;
      case DATE_OF_ISSUE:
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
        changeParagraph(docVariable.getParagraph(), diploma.getModeOfStudy().getName(), true);
        break;
      case CREDITS_GAINED:
        changeParagraph(docVariable.getParagraph(), String.valueOf(diploma.getCreditsGained()),
            false);
        break;
      case CLASSIFICATION_SYSTEM:
        changeParagraph(docVariable.getParagraph(), diploma.getClassificationSystem().getName(),
            true);
        break;
      case CLASSIFICATION_SYSTEM_DESCRIPTION:
        changeParagraph(docVariable.getParagraph(),
            diploma.getClassificationSystem().getCriteria(), true);
        break;
      case PROFESSIONAL_STATUS:
        changeParagraph(docVariable.getParagraph(), diploma.getProfessionalStatus().getName(),
            true);
        break;
      case DURATION_OF_TRAINING:
        changeParagraph(docVariable.getParagraph(), diploma.getDurationOfTraining().getName(),
            true);
        break;
      case INFORMATION_ON_CERTIFICATION:
        changeParagraph(docVariable.getParagraph(), diploma.getInformationOnCertification(), false);
        break;
      case PREVIOUS_DOCUMENT:
        changeParagraph(docVariable.getParagraph(),
            diploma.getStudent().getPreviousDocument().getName(), true);
        break;
      case DATE:
        changeParagraph(docVariable.getParagraph(),
            new SimpleDateFormat("dd/MM/yyyy").format(diploma.getDateOfIssue()), false);
        break;
    }
  }

  private void changeParagraph(XWPFParagraph paragraph, String value, boolean splitAndSetItalic) {
    paragraph.getRuns().forEach(xwpfRun -> xwpfRun.setText("", 0));
    if (splitAndSetItalic) {
      final String[] s = value.split("/");
      if (s.length == 2) {
        paragraph.getRuns().get(0).setText(s[0] + " /", 0);
        paragraph.getRuns().get(1).setText(s[1], 0);
        paragraph.getRuns().get(1).setItalic(true);
      }
    } else {
      paragraph.getRuns().get(0).setText(value, 0);
    }
    //Todo split value("/") and add new line with second value
  }
}
