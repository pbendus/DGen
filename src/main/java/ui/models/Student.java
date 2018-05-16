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
    private ObjectProperty<DiplomaSubject> diplomaSubject;
    private ObjectProperty<PreviousDocument> previousDocument;

    private StringProperty fullName;
    private CheckBox select;

    public Student() {
    }

    public Student(int id, String familyName, String givenName, String familyNameTr,
                   String givenNameTr, Date dateOfBirth, Protocol protocol,
                   DiplomaSubject diplomaSubject, PreviousDocument previousDocument) {
        this.id = new SimpleIntegerProperty(id);
        this.familyName = new SimpleStringProperty(familyName);
        this.givenName = new SimpleStringProperty(givenName);
        this.familyNameTr = new SimpleStringProperty(familyNameTr);
        this.givenNameTr = new SimpleStringProperty(givenNameTr);
        this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
        this.protocol = new SimpleObjectProperty<>(protocol);
        this.diplomaSubject = new SimpleObjectProperty<>(diplomaSubject);
        this.previousDocument = new SimpleObjectProperty<>(previousDocument);

        this.fullName = new SimpleStringProperty(this.familyName.get() + " " + this.givenName.get());
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
    }

    public String getGivenName() {
        return givenName.get();
    }

    public StringProperty givenNameProperty() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName.set(givenName);
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

    public DiplomaSubject getDiplomaSubject() {
        return diplomaSubject.get();
    }

    public ObjectProperty<DiplomaSubject> diplomaSubjectProperty() {
        return diplomaSubject;
    }

    public void setDiplomaSubject(DiplomaSubject diplomaSubject) {
        this.diplomaSubject.set(diplomaSubject);
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

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public CheckBox getSelect() {
        return select;
    }
}
