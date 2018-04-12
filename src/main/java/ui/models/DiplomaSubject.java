package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DiplomaSubject {

    private IntegerProperty id;
    private StringProperty subjectUK;
    private StringProperty subjectEN;

    public DiplomaSubject() {
    }

    public DiplomaSubject(int id, String subjectUK, String subjectEN) {
        this.id = new SimpleIntegerProperty(id);
        this.subjectUK = new SimpleStringProperty(subjectUK);
        this.subjectEN = new SimpleStringProperty(subjectEN);
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

    public String getSubjectUK() {
        return subjectUK.get();
    }

    public StringProperty subjectUKProperty() {
        return subjectUK;
    }

    public void setSubjectUK(String subjectUK) {
        this.subjectUK.set(subjectUK);
    }

    public String getSubjectEN() {
        return subjectEN.get();
    }

    public StringProperty subjectENProperty() {
        return subjectEN;
    }

    public void setSubjectEN(String subjectEN) {
        this.subjectEN.set(subjectEN);
    }
}
