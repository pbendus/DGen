package ui.models;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.Date;

public class Diploma {

    private IntegerProperty id;
    private StringProperty number;
    private StringProperty registrationNumber;
    private ObjectProperty<Date> dateOfIssue;
    private ObjectProperty<Student> student;
    private ObjectProperty<MainField> mainField;
    private ObjectProperty<FieldOfStudy> fieldOfStudy;
    private ObjectProperty<OfficialDurationOfProgramme> officialDurationOfProgramme;
    private ObjectProperty<AccessRequirements> accessRequirements;
    private ObjectProperty<ModeOfStudy> modeOfStudy;
    private ObjectProperty<ProfessionalStatus> professionalStatus;
    private ObjectProperty<ClassificationSystem> classificationSystem;
    private ObjectProperty<DurationOfTraining> durationOfTraining;
    private ObservableList<EducationalComponent> educationalComponents;

    public Diploma() {
    }

    public Diploma(int id, String number, String registrationNumber, Date dateOfIssue,
                   Student student, MainField mainField, FieldOfStudy fieldOfStudy,
                   OfficialDurationOfProgramme officialDurationOfProgramme,
                   AccessRequirements accessRequirements, ModeOfStudy modeOfStudy,
                   ProfessionalStatus professionalStatus, ClassificationSystem classificationSystem,
                   DurationOfTraining durationOfTraining) {
        this.id = new SimpleIntegerProperty(id);
        this.number = new SimpleStringProperty(number);
        this.registrationNumber = new SimpleStringProperty(registrationNumber);
        this.dateOfIssue = new SimpleObjectProperty<>(dateOfIssue);
        this.student = new SimpleObjectProperty<>(student);
        this.mainField = new SimpleObjectProperty<>(mainField);
        this.fieldOfStudy = new SimpleObjectProperty<>(fieldOfStudy);
        this.officialDurationOfProgramme = new SimpleObjectProperty<>(officialDurationOfProgramme);
        this.accessRequirements = new SimpleObjectProperty<>(accessRequirements);
        this.modeOfStudy = new SimpleObjectProperty<>(modeOfStudy);
        this.professionalStatus = new SimpleObjectProperty<>(professionalStatus);
        this.classificationSystem = new SimpleObjectProperty<>(classificationSystem);
        this.durationOfTraining = new SimpleObjectProperty<>(durationOfTraining);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getRegistrationNumber() {
        return registrationNumber.get();
    }

    public StringProperty registrationNumberProperty() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber.set(registrationNumber);
    }

    public Date getDateOfIssue() {
        return dateOfIssue.get();
    }

    public ObjectProperty<Date> dateOfIssueProperty() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue.set(dateOfIssue);
    }

    public Student getStudent() {
        return student.get();
    }

    public ObjectProperty<Student> studentProperty() {
        return student;
    }

    public void setStudent(Student student) {
        this.student.set(student);
    }

    public MainField getMainField() {
        return mainField.get();
    }

    public ObjectProperty<MainField> mainFieldProperty() {
        return mainField;
    }

    public void setMainField(MainField mainField) {
        this.mainField.set(mainField);
    }

    public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy.get();
    }

    public ObjectProperty<FieldOfStudy> fieldOfStudyProperty() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy.set(fieldOfStudy);
    }

    public OfficialDurationOfProgramme getOfficialDurationOfProgramme() {
        return officialDurationOfProgramme.get();
    }

    public ObjectProperty<OfficialDurationOfProgramme> officialDurationOfProgrammeProperty() {
        return officialDurationOfProgramme;
    }

    public void setOfficialDurationOfProgramme(OfficialDurationOfProgramme officialDurationOfProgramme) {
        this.officialDurationOfProgramme.set(officialDurationOfProgramme);
    }

    public AccessRequirements getAccessRequirements() {
        return accessRequirements.get();
    }

    public ObjectProperty<AccessRequirements> accessRequirementsProperty() {
        return accessRequirements;
    }

    public void setAccessRequirements(AccessRequirements accessRequirements) {
        this.accessRequirements.set(accessRequirements);
    }

    public ModeOfStudy getModeOfStudy() {
        return modeOfStudy.get();
    }

    public ObjectProperty<ModeOfStudy> modeOfStudyProperty() {
        return modeOfStudy;
    }

    public void setModeOfStudy(ModeOfStudy modeOfStudy) {
        this.modeOfStudy.set(modeOfStudy);
    }

    public ProfessionalStatus getProfessionalStatus() {
        return professionalStatus.get();
    }

    public ObjectProperty<ProfessionalStatus> professionalStatusProperty() {
        return professionalStatus;
    }

    public void setProfessionalStatus(ProfessionalStatus professionalStatus) {
        this.professionalStatus.set(professionalStatus);
    }

    public ClassificationSystem getClassificationSystem() {
        return classificationSystem.get();
    }

    public ObjectProperty<ClassificationSystem> classificationSystemProperty() {
        return classificationSystem;
    }

    public void setClassificationSystem(ClassificationSystem classificationSystem) {
        this.classificationSystem.set(classificationSystem);
    }

    public DurationOfTraining getDurationOfTraining() {
        return durationOfTraining.get();
    }

    public ObjectProperty<DurationOfTraining> durationOfTrainingProperty() {
        return durationOfTraining;
    }

    public void setDurationOfTraining(DurationOfTraining durationOfTraining) {
        this.durationOfTraining.set(durationOfTraining);
    }

    public ObservableList<EducationalComponent> getEducationalComponents() {
        return educationalComponents;
    }

    public void setEducationalComponents(ObservableList<EducationalComponent> educationalComponents) {
        this.educationalComponents = educationalComponents;
    }
}
