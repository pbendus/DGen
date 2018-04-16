package doc_utils;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public class DocVariable {
  private String value;
  private XWPFParagraph paragraph;
  private XWPFTableCell cell;
  private DocVariableConst docVariableConst;

  public DocVariable(String value, XWPFParagraph paragraph, XWPFTableCell cell,
      DocVariableConst docVariableConst) {
    this.value = value;
    this.paragraph = paragraph;
    this.cell = cell;
    this.docVariableConst = docVariableConst;
  }

  public DocVariable(String value, XWPFParagraph paragraph,
      DocVariableConst docVariableConst) {
    this.value = value;
    this.paragraph = paragraph;
    this.docVariableConst = docVariableConst;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public XWPFParagraph getParagraph() {
    return paragraph;
  }

  public void setParagraph(XWPFParagraph paragraph) {
    this.paragraph = paragraph;
  }

  public XWPFTableCell getCell() {
    return cell;
  }

  public void setCell(XWPFTableCell cell) {
    this.cell = cell;
  }

  public DocVariableConst getDocVariableConst() {
    return docVariableConst;
  }

  public void setDocVariableConst(DocVariableConst docVariableConst) {
    this.docVariableConst = docVariableConst;
  }
}
