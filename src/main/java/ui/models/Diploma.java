package ui.models;

import javafx.beans.property.*;

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
    private ObjectProperty<EctsCredits> ectsCredits;
    private ObjectProperty<ClassificationSystem> classificationSystem;
    private ObjectProperty<DurationOfTraining> durationOfTraining;
    private ObjectProperty<DiplomaSubject> diplomaSubject;

    public Diploma() {
        this.id = new SimpleIntegerProperty();
        this.number = new SimpleStringProperty();
        this.registrationNumber = new SimpleStringProperty();
        this.dateOfIssue = new SimpleObjectProperty<>();
        this.student = new SimpleObjectProperty<>();
        this.mainField = new SimpleObjectProperty<>();
        this.fieldOfStudy = new SimpleObjectProperty<>();
        this.officialDurationOfProgramme = new SimpleObjectProperty<>();
        this.accessRequirements = new SimpleObjectProperty<>();
        this.ectsCredits = new SimpleObjectProperty<>();
        this.classificationSystem = new SimpleObjectProperty<>();
        this.durationOfTraining = new SimpleObjectProperty<>();
        this.diplomaSubject = new SimpleObjectProperty<>();
    }

    public Diploma(int id, String number, String registrationNumber, Date dateOfIssue,
        Student student, MainField mainField, FieldOfStudy fieldOfStudy,
        OfficialDurationOfProgramme officialDurationOfProgramme,
        AccessRequirements accessRequirements,
        EctsCredits ectsCredits, ClassificationSystem classificationSystem,
        DurationOfTraining durationOfTraining,
        DiplomaSubject diplomaSubject) {
        this.id = new SimpleIntegerProperty(id);
        this.number = new SimpleStringProperty(number);
        this.registrationNumber = new SimpleStringProperty(registrationNumber);
        this.dateOfIssue = new SimpleObjectProperty<>(dateOfIssue);
        this.student = new SimpleObjectProperty<>(student);
        this.mainField = new SimpleObjectProperty<>(mainField);
        this.fieldOfStudy = new SimpleObjectProperty<>(fieldOfStudy);
        this.officialDurationOfProgramme = new SimpleObjectProperty<>(officialDurationOfProgramme);
        this.accessRequirements = new SimpleObjectProperty<>(accessRequirements);
        this.ectsCredits = new SimpleObjectProperty<>(ectsCredits);
        this.classificationSystem = new SimpleObjectProperty<>(classificationSystem);
        this.durationOfTraining = new SimpleObjectProperty<>(durationOfTraining);
        this.diplomaSubject = new SimpleObjectProperty<>(diplomaSubject);
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

    public EctsCredits getEctsCredits() {
        return ectsCredits.get();
    }

    public ObjectProperty<EctsCredits> ectsCreditsProperty() {
        return ectsCredits;
    }

    public void setEctsCredits(EctsCredits ectsCredits) {
        this.ectsCredits.set(ectsCredits);
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

    public DiplomaSubject getDiplomaSubject() {
        return diplomaSubject.get();
    }

    public ObjectProperty<DiplomaSubject> diplomaSubjectProperty() {
        return diplomaSubject;
    }

    public void setDiplomaSubject(DiplomaSubject diplomaSubject) {
        this.diplomaSubject.set(diplomaSubject);
    }
}
