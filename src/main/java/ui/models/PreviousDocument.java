package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PreviousDocument {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty nameEN;

    public PreviousDocument() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.nameEN = new SimpleStringProperty();
    }

    public PreviousDocument(int id, String name, String nameEN) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return name.get();
    }

    public String getNameEN() {
        return nameEN.get();
    }

    public void setNameEN(String nameEN) {
        this.nameEN.set(nameEN);
    }

    public StringProperty nameENProperty() {
        return nameEN;
    }
}
