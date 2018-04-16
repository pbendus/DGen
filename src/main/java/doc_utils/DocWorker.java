package doc_utils;

import db.entities.Diploma;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocWorker {
  @Value("${doc.pattern}")
  private String key;

  private final XWPFDocument document;

  public DocWorker(XWPFDocument document) {
    this.document = document;
  }

  private boolean isVariable(String string) {
    if (string.length() < 4) {
      return false;
    }
    final String regex = ".*" + key + ".*" + key + ".*";

    final Pattern pattern = Pattern.compile(regex);
    final Matcher matcher = pattern.matcher(string);

    return matcher.find();
  }

  private Set<DocVariable> findAllVariables() {
    final Set<DocVariable> docVariables = new HashSet<>();

    for (XWPFParagraph paragraph : document.getParagraphs()) {
      final List<XWPFRun> runs = paragraph.getRuns();
      if (runs != null) {
        final String text = paragraph.getText();
        if (text != null && isVariable(text)) {
          for (DocVariableConst docVariableConst :
              DocVariableConst.values()) {
            final String variable = text.replaceAll(key, "");
            if (docVariableConst.getValue().equals(variable)) {
              docVariables.add(new DocVariable(text, paragraph, docVariableConst));
              System.out.println(text);
            }
          }
        }
      }
    }

    for (XWPFTable table : document.getTables()) {
      for (XWPFTableRow row : table.getRows()) {
        for (XWPFTableCell cell : row.getTableCells()) {
          for (XWPFParagraph paragraph : cell.getParagraphs()) {
            final String text = paragraph.getText();
            if (text != null && isVariable(text)) {
              for (DocVariableConst docVariableConst :
                  DocVariableConst.values()) {
                final String variable = text.replaceAll(key, "");
                if (docVariableConst.getValue().equals(variable)) {
                  System.out.println(text);
                  docVariables.add(new DocVariable(text, paragraph, cell, docVariableConst));
                }
              }
            }
          }
        }
      }
    }

    return docVariables;
  }

  private void saveDocument(String path) throws IOException {
    document.write(new FileOutputStream(path));
  }

  public void generateDocument(Diploma diploma) throws IOException {
    final Set<DocVariable> variables = findAllVariables();

    for (DocVariable docVariable :
        variables) {
      changeParagraph(docVariable, diploma);
    }

    //TODO add rows with education components

    saveDocument(diploma.getNumber() + ".docx");
  }

  private void changeParagraph(DocVariable docVariable, Diploma diploma) {
    switch (docVariable.getDocVariableConst()) {
      case DIPLOMA:
        changeParagraph(docVariable.getParagraph(), diploma.getNumber());
        break;
      case REGISTRATION_NUMBER:
        changeParagraph(docVariable.getParagraph(), diploma.getRegistrationNumber());
        break;
      case DATE_OF_ISSUE:
        changeParagraph(docVariable.getParagraph(),
            new SimpleDateFormat("dd.MM.yyyy").format(diploma.getDateOfIssue()));
        break;
      case FAMILY_NAME:
        changeParagraph(docVariable.getParagraph(), diploma.getStudent().getFamilyName());
        break;
      case GIVEN_NAME:
        changeParagraph(docVariable.getParagraph(), diploma.getStudent().getGivenName());
        break;
      case FAMILY_NAME_TR:
        changeParagraph(docVariable.getParagraph(), diploma.getStudent().getFamilyNameTr());
        break;
      case GIVEN_NAME_TR:
        changeParagraph(docVariable.getParagraph(), diploma.getStudent().getGivenNameTr());
        break;
      case DATE_OF_BIRTH:
        changeParagraph(docVariable.getParagraph(),
            new SimpleDateFormat("dd/MM/yyyy").format(diploma.getStudent().getDateOfBirth()));
        break;
      case MAIN_FIELD:
        changeParagraph(docVariable.getParagraph(), diploma.getMainField().getName());
        break;
      case FIELD_OF_STUDY:
        changeParagraph(docVariable.getParagraph(), diploma.getFieldOfStudy().getName());
        break;
      case OFFICIAL_DURATION_OF_PROGRAMME:
        changeParagraph(docVariable.getParagraph(),
            diploma.getOfficialDurationOfProgramme().getName());
        break;
      case ACCESS_REQUIREMENTS:
        changeParagraph(docVariable.getParagraph(), diploma.getAccessRequirements().getName());
        break;
      case MODE_OF_STUDY:
        changeParagraph(docVariable.getParagraph(), diploma.getModeOfStudy().getName());
        break;
      case CREDITS_GAINED:
        changeParagraph(docVariable.getParagraph(), String.valueOf(diploma.getCreditsGained()));
        break;
      case CLASSIFICATION_SYSTEM:
        changeParagraph(docVariable.getParagraph(), diploma.getClassificationSystem().getName());
        break;
      case CLASSIFICATION_SYSTEM_DESCRIPTION:
        changeParagraph(docVariable.getParagraph(),
            diploma.getClassificationSystem().getCriteria());
        break;
      case PROFESSIONAL_STATUS:
        changeParagraph(docVariable.getParagraph(), diploma.getProfessionalStatus().getName());
        break;
      case DURATION_OF_TRAINING:
        changeParagraph(docVariable.getParagraph(), diploma.getDurationOfTraining().getName());
        break;
      case INFORMATION_ON_CERTIFICATION:
        changeParagraph(docVariable.getParagraph(), diploma.getInformationOnCertification());
        break;
      case PREVIOUS_DOCUMENT:
        changeParagraph(docVariable.getParagraph(),
            diploma.getStudent().getPreviousDocument().getName());
        break;
      case DATE:
        changeParagraph(docVariable.getParagraph(),
            new SimpleDateFormat("dd/MM/yyyy").format(diploma.getDateOfIssue()));
        break;
    }
  }

  private void changeParagraph(XWPFParagraph paragraph, String value) {
    paragraph.getRuns().forEach(xwpfRun -> xwpfRun.setText("", 0));
    paragraph.getRuns().get(0).setText(value, 0);
    //Todo split value("/") and add new line with second value
  }
}
