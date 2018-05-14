package db.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DatabaseTable(tableName = "diploma")
public class Diploma {

  @DatabaseField(generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
  private String number;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "registration_number")
  private String registrationNumber;

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

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "mode_of_study_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private ModeOfStudy modeOfStudy;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "professional_status_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private ProfessionalStatus professionalStatus;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "classification_system_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private ClassificationSystem classificationSystem;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "duration_of_training_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private DurationOfTraining durationOfTraining;

  @ForeignCollectionField()
  private ForeignCollection<EducationalComponent> educationalComponents;

  public Diploma() {
  }

  public Diploma(int id, String number, String registrationNumber, Date dateOfIssue,
      Student student, MainField mainField, FieldOfStudy fieldOfStudy,
      OfficialDurationOfProgramme officialDurationOfProgramme,
      AccessRequirements accessRequirements, ModeOfStudy modeOfStudy,
      ProfessionalStatus professionalStatus, ClassificationSystem classificationSystem,
      DurationOfTraining durationOfTraining) {
    this.id = id;
    this.number = number;
    this.registrationNumber = registrationNumber;
    this.dateOfIssue = dateOfIssue;
    this.student = student;
    this.mainField = mainField;
    this.fieldOfStudy = fieldOfStudy;
    this.officialDurationOfProgramme = officialDurationOfProgramme;
    this.accessRequirements = accessRequirements;
    this.modeOfStudy = modeOfStudy;
    this.professionalStatus = professionalStatus;
    this.classificationSystem = classificationSystem;
    this.durationOfTraining = durationOfTraining;
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

  public ModeOfStudy getModeOfStudy() {
    return modeOfStudy;
  }

  public void setModeOfStudy(ModeOfStudy modeOfStudy) {
    this.modeOfStudy = modeOfStudy;
  }

  public ProfessionalStatus getProfessionalStatus() {
    return professionalStatus;
  }

  public void setProfessionalStatus(ProfessionalStatus professionalStatus) {
    this.professionalStatus = professionalStatus;
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

  public ForeignCollection<EducationalComponent> getEducationalComponents() {
    return educationalComponents;
  }

  public int getCreditsGained() {
    int value = 0;
    for (EducationalComponent component :
        getEducationalComponents()) {
      value += component.getCredit();
    }
    return value;
  }

  public String getInformationOnCertification() {
    return String.format("Магістерська робота - %s (%s) / Master’s Thesis - %s (%s)",
        student.getDiplomaSubject().getSubjectUK(), student.getProtocol().getNameUK(),
        student.getDiplomaSubject().getSubjectEN(), student.getProtocol().getNameEN());
  }

  public List<EducationalComponent> getAllCourses() {
    List<EducationalComponent> educationalComponents = new ArrayList<>();
    for (EducationalComponent component :
        this.educationalComponents) {
      if (component.getEducationalComponentType()
          .getName()
          .equals(EducationalComponentTypeConst.COURSE)) {
        educationalComponents.add(component);
      }
    }

    return educationalComponents;
  }

  public List<EducationalComponent> getAllResearchProjects() {
    List<EducationalComponent> educationalComponents = new ArrayList<>();
    for (EducationalComponent component :
        this.educationalComponents) {
      if (component.getEducationalComponentType()
          .getName()
          .equals(EducationalComponentTypeConst.RESEARCH_PROJECT)) {
        educationalComponents.add(component);
      }
    }

    return educationalComponents;
  }

  public List<EducationalComponent> getAllInternships() {
    List<EducationalComponent> educationalComponents = new ArrayList<>();
    for (EducationalComponent component :
        this.educationalComponents) {
      if (component.getEducationalComponentType()
          .getName()
          .equals(EducationalComponentTypeConst.INTERNSHIP)) {
        educationalComponents.add(component);
      }
    }

    return educationalComponents;
  }

  public List<EducationalComponent> getAllStateAttestations() {
    List<EducationalComponent> educationalComponents = new ArrayList<>();
    for (EducationalComponent component :
        this.educationalComponents) {
      if (component.getEducationalComponentType()
          .getName()
          .equals(EducationalComponentTypeConst.STATE_ATTESTATION)) {
        educationalComponents.add(component);
      }
    }

    return educationalComponents;
  }
}
