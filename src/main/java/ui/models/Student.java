package ui.models;

import javafx.beans.property.*;
import javafx.scene.control.CheckBox;

import java.util.Date;

public class Student {

    private IntegerProperty id;
    private StringProperty familyName;
    private StringProperty givenName;
    private StringProperty familyNameTr;
    private StringProperty givenNameTr;
    private ObjectProperty<Date> dateOfBirth;
    private ObjectProperty<Protocol> protocol;
    private ObjectProperty<PreviousDocument> previousDocument;
    private ObjectProperty<ModeOfStudy> modeOfStudyObject;
    private ObjectProperty<DurationOfStudy> durationOfStudy;
    private ObjectProperty<Group> group;

    private StringProperty fullName;
    private CheckBox select;

    public Student() {
        this.id = new SimpleIntegerProperty();
        this.familyName = new SimpleStringProperty();
        this.givenName = new SimpleStringProperty();
        this.familyNameTr = new SimpleStringProperty();
        this.givenNameTr = new SimpleStringProperty();
        this.dateOfBirth = new SimpleObjectProperty<>();
        this.protocol = new SimpleObjectProperty<>();
        this.previousDocument = new SimpleObjectProperty<>();
        this.modeOfStudyObject = new SimpleObjectProperty<>();
        this.durationOfStudy = new SimpleObjectProperty<>();
        this.group = new SimpleObjectProperty<>();
        this.select = new CheckBox();
    }

    public Student(int id, String familyName, String givenName, String familyNameTr,
        String givenNameTr, Date dateOfBirth, Protocol protocol,
        PreviousDocument previousDocument,
        ModeOfStudy modeOfStudyObject,
        DurationOfStudy durationOfStudy,
        Group group) {
        this.id = new SimpleIntegerProperty(id);
        this.familyName = new SimpleStringProperty(familyName);
        this.givenName = new SimpleStringProperty(givenName);
        this.familyNameTr = new SimpleStringProperty(familyNameTr);
        this.givenNameTr = new SimpleStringProperty(givenNameTr);
        this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
        this.protocol = new SimpleObjectProperty<>(protocol);
        this.previousDocument = new SimpleObjectProperty<>(previousDocument);
        this.modeOfStudyObject = new SimpleObjectProperty<>(modeOfStudyObject);
        this.durationOfStudy = new SimpleObjectProperty<>(durationOfStudy);
        this.group = new SimpleObjectProperty<>(group);

        updateFullName();
        this.select = new CheckBox();
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

    public String getFamilyName() {
        return familyName.get();
    }

    public StringProperty familyNameProperty() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName.set(familyName);
        updateFullName();
    }

    public String getGivenName() {
        return givenName.get();
    }

    public StringProperty givenNameProperty() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName.set(givenName);
        updateFullName();
    }

    public String getFamilyNameTr() {
        return familyNameTr.get();
    }

    public StringProperty familyNameTrProperty() {
        return familyNameTr;
    }

    public void setFamilyNameTr(String familyNameTr) {
        this.familyNameTr.set(familyNameTr);
    }

    public String getGivenNameTr() {
        return givenNameTr.get();
    }

    public StringProperty givenNameTrProperty() {
        return givenNameTr;
    }

    public void setGivenNameTr(String givenNameTr) {
        this.givenNameTr.set(givenNameTr);
    }

    public Date getDateOfBirth() {
        return dateOfBirth.get();
    }

    public ObjectProperty<Date> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public Protocol getProtocol() {
        return protocol.get();
    }

    public ObjectProperty<Protocol> protocolProperty() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol.set(protocol);
    }

    public PreviousDocument getPreviousDocument() {
        return previousDocument.get();
    }

    public ObjectProperty<PreviousDocument> previousDocumentProperty() {
        return previousDocument;
    }

    public void setPreviousDocument(PreviousDocument previousDocument) {
        this.previousDocument.set(previousDocument);
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void updateFullName() {
        if (fullName == null) {
            fullName = new SimpleStringProperty(this.familyName + " " + this.givenName);
        } else {
            fullName.set(this.familyName.getValue() + " " + this.givenName.getValue());
        }
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public CheckBox getSelect() {
        return select;
    }

    public ModeOfStudy getModeOfStudy() {
        return modeOfStudyObject.get();
    }

    public ObjectProperty<ModeOfStudy> modeOfStudyObjectProperty() {
        return modeOfStudyObject;
    }

    public void setModeOfStudyObject(ModeOfStudy modeOfStudyObject) {
        this.modeOfStudyObject.set(modeOfStudyObject);
    }

    public DurationOfStudy getDurationOfStudy() {
        return durationOfStudy.get();
    }

    public ObjectProperty<DurationOfStudy> durationOfStudyProperty() {
        return durationOfStudy;
    }

    public void setDurationOfStudy(DurationOfStudy durationOfStudy) {
        this.durationOfStudy.set(durationOfStudy);
    }

    public Group getGroup() {
        return group.get();
    }

    public ObjectProperty<Group> groupProperty() {
        return group;
    }

    public void setGroup(Group group) {
        this.group.set(group);
    }
}
