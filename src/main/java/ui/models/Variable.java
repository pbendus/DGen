package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variable {

    private IntegerProperty id;
    private StringProperty variable;
    private StringProperty description;

    public Variable() {
        this.id = new SimpleIntegerProperty(0);
        this.variable = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
    }

    public Variable(int id, String variable, String description) {
        this.id.set(id);
        this.variable.set(variable);
        this.description.set(description);
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

    public String getVariable() {
        return variable.get();
    }

    public void setVariable(String variable) {
        this.variable.set(variable);
    }

    public StringProperty variableProperty() {
        return variable;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }
}
