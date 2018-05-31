package db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "student")
public class Student {

  @DatabaseField(generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "family_name")
  private String familyName;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "given_name")
  private String givenName;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "family_name_tr")
  private String familyNameTr;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "given_name_tr")
  private String givenNameTr;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "date_of_birth",
      dataType = DataType.DATE_STRING, format = "yyyy-MM-dd")
  private Date dateOfBirth;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "protocol_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private Protocol protocol;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "diploma_subject_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private DiplomaSubject diplomaSubject;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "previous_document_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private PreviousDocument previousDocument;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "mode_of_study_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private ModeOfStudy modeOfStudy;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "duration_of_study_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private DurationOfStudy durationOfStudy;

  public Student() {
  }

  public Student(int id, String familyName, String givenName, String familyNameTr,
      String givenNameTr, Date dateOfBirth, Protocol protocol,
      DiplomaSubject diplomaSubject, PreviousDocument previousDocument,
      ModeOfStudy modeOfStudy, DurationOfStudy durationOfStudy) {
    this.id = id;
    this.familyName = familyName;
    this.givenName = givenName;
    this.familyNameTr = familyNameTr;
    this.givenNameTr = givenNameTr;
    this.dateOfBirth = dateOfBirth;
    this.protocol = protocol;
    this.diplomaSubject = diplomaSubject;
    this.previousDocument = previousDocument;
    this.modeOfStudy = modeOfStudy;
    this.durationOfStudy = durationOfStudy;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getFamilyNameTr() {
    return familyNameTr;
  }

  public void setFamilyNameTr(String familyNameTr) {
    this.familyNameTr = familyNameTr;
  }

  public String getGivenNameTr() {
    return givenNameTr;
  }

  public void setGivenNameTr(String givenNameTr) {
    this.givenNameTr = givenNameTr;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public void setProtocol(Protocol protocol) {
    this.protocol = protocol;
  }

  public DiplomaSubject getDiplomaSubject() {
    return diplomaSubject;
  }

  public void setDiplomaSubject(DiplomaSubject diplomaSubject) {
    this.diplomaSubject = diplomaSubject;
  }

  public PreviousDocument getPreviousDocument() {
    return previousDocument;
  }

  public void setPreviousDocument(PreviousDocument previousDocument) {
    this.previousDocument = previousDocument;
  }

  public ModeOfStudy getModeOfStudy() {
    return modeOfStudy;
  }

  public void setModeOfStudy(ModeOfStudy modeOfStudy) {
    this.modeOfStudy = modeOfStudy;
  }

  public DurationOfStudy getDurationOfStudy() {
    return durationOfStudy;
  }

  public void setDurationOfStudy(DurationOfStudy durationOfStudy) {
    this.durationOfStudy = durationOfStudy;
  }
}
