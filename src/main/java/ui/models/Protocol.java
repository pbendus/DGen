package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Protocol {

    private IntegerProperty id;
    private StringProperty nameUK;
    private StringProperty nameEN;

    public Protocol() {
        this.id = new SimpleIntegerProperty();
        this.nameUK = new SimpleStringProperty();
        this.nameEN = new SimpleStringProperty();
    }

    public Protocol(int id, String nameUK, String nameEN) {
        this.id = new SimpleIntegerProperty(id);
        this.nameUK = new SimpleStringProperty(nameUK);
        this.nameEN = new SimpleStringProperty(nameEN);
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

    public String getNameUK() {
        return nameUK.get();
    }

    public StringProperty nameUKProperty() {
        return nameUK;
    }

    public void setNameUK(String nameUK) {
        this.nameUK.set(nameUK);
    }

    public String getNameEN() {
        return nameEN.get();
    }

    public StringProperty nameENProperty() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN.set(nameEN);
    }

    @Override
    public String toString() {
        return nameUK.get();
    }
}
