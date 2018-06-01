package db.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "diploma")
public class Diploma {

  @DatabaseField(generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
  private String number;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "registration_number")
  private String registrationNumber;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "addition_registration_number")
  private String additionRegistrationNumber;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "date_of_issue",
      dataType = DataType.DATE_STRING, format = "yyyy-MM-dd")
  private Date dateOfIssue;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "student_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true, unique = true)
  private Student student;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "main_field_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private MainField mainField;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "field_of_study_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private FieldOfStudy fieldOfStudy;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "official_duration_of_programme_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private OfficialDurationOfProgramme officialDurationOfProgramme;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "access_requirements_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private AccessRequirements accessRequirements;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "classification_system_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private ClassificationSystem classificationSystem;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "duration_of_training_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private DurationOfTraining durationOfTraining;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "ects_credits_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private EctsCredits ectsCredits;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "diploma_subject_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private DiplomaSubject diplomaSubject;

  public Diploma() {
  }

  public Diploma(int id, String number, String registrationNumber,
      String additionRegistrationNumber, Date dateOfIssue,
      Student student, MainField mainField, FieldOfStudy fieldOfStudy,
      OfficialDurationOfProgramme officialDurationOfProgramme,
      AccessRequirements accessRequirements,
      ClassificationSystem classificationSystem,
      DurationOfTraining durationOfTraining, EctsCredits ectsCredits,
      DiplomaSubject diplomaSubject) {
    this.id = id;
    this.number = number;
    this.registrationNumber = registrationNumber;
    this.additionRegistrationNumber = additionRegistrationNumber;
    this.dateOfIssue = dateOfIssue;
    this.student = student;
    this.mainField = mainField;
    this.fieldOfStudy = fieldOfStudy;
    this.officialDurationOfProgramme = officialDurationOfProgramme;
    this.accessRequirements = accessRequirements;
    this.classificationSystem = classificationSystem;
    this.durationOfTraining = durationOfTraining;
    this.ectsCredits = ectsCredits;
    this.diplomaSubject = diplomaSubject;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public Date getDateOfIssue() {
    return dateOfIssue;
  }

  public void setDateOfIssue(Date dateOfIssue) {
    this.dateOfIssue = dateOfIssue;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public MainField getMainField() {
    return mainField;
  }

  public void setMainField(MainField mainField) {
    this.mainField = mainField;
  }

  public FieldOfStudy getFieldOfStudy() {
    return fieldOfStudy;
  }

  public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
    this.fieldOfStudy = fieldOfStudy;
  }

  public OfficialDurationOfProgramme getOfficialDurationOfProgramme() {
    return officialDurationOfProgramme;
  }

  public void setOfficialDurationOfProgramme(
      OfficialDurationOfProgramme officialDurationOfProgramme) {
    this.officialDurationOfProgramme = officialDurationOfProgramme;
  }

  public AccessRequirements getAccessRequirements() {
    return accessRequirements;
  }

  public void setAccessRequirements(AccessRequirements accessRequirements) {
    this.accessRequirements = accessRequirements;
  }

  public ClassificationSystem getClassificationSystem() {
    return classificationSystem;
  }

  public void setClassificationSystem(ClassificationSystem classificationSystem) {
    this.classificationSystem = classificationSystem;
  }

  public DurationOfTraining getDurationOfTraining() {
    return durationOfTraining;
  }

  public void setDurationOfTraining(DurationOfTraining durationOfTraining) {
    this.durationOfTraining = durationOfTraining;
  }

  public EctsCredits getEctsCredits() {
    return ectsCredits;
  }

  public void setEctsCredits(EctsCredits ectsCredits) {
    this.ectsCredits = ectsCredits;
  }

  public String getAdditionRegistrationNumber() {
    return additionRegistrationNumber;
  }

  public void setAdditionRegistrationNumber(String additionRegistrationNumber) {
    this.additionRegistrationNumber = additionRegistrationNumber;
  }

  public String getInformationOnCertification() {
    return String.format("Бакалаврська робота - %s (%s) / Bachelor's Thesis - %s (%s)",
        getDiplomaSubject().getSubjectUK(), student.getProtocol().getNameUK(),
        getDiplomaSubject().getSubjectEN(), student.getProtocol().getNameEN());
  }

  public DiplomaSubject getDiplomaSubject() {
    return diplomaSubject;
  }

  public void setDiplomaSubject(DiplomaSubject diplomaSubject) {
    this.diplomaSubject = diplomaSubject;
  }
}
