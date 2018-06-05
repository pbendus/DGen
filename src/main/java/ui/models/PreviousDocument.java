package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PreviousDocument {

    private IntegerProperty id;
    private StringProperty nameUk;
    private StringProperty nameEn;

    public PreviousDocument() {
        this.id = new SimpleIntegerProperty();
        this.nameUk = new SimpleStringProperty();
    }

    public PreviousDocument(int id, String nameUk, String nameEn) {
        this.id = new SimpleIntegerProperty(id);
        this.nameUk = new SimpleStringProperty(nameUk);
        this.nameEn = new SimpleStringProperty(nameEn);
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

    public String getNameUk() {
        return nameUk.get();
    }

    public void setNameUk(String nameUk) {
        this.nameUk.set(nameUk);
    }

    public StringProperty nameUkProperty() {
        return nameUk;
    }

    public String getNameEn() {
        return nameEn.get();
    }

    public void setNameEn(String nameEn) {
        this.nameEn.set(nameEn);
    }

    public StringProperty nameEnProperty() {
        return nameEn;
    }

    @Override
    public String toString() {
        return nameUk.get();
    }
}
