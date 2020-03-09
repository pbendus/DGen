package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DurationOfStudy {
    private IntegerProperty id;
    private StringProperty value;

    public DurationOfStudy() {
        this.id = new SimpleIntegerProperty();
        this.value = new SimpleStringProperty();
    }

    public DurationOfStudy(int id, String value) {
        this.id = new SimpleIntegerProperty(id);
        this.value = new SimpleStringProperty(value);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String name) {
        this.value.set(name);
    }

    public StringProperty nameProperty() {
        return value;
    }

    @Override
    public String toString() {
        return value.get();
    }
}
