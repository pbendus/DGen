package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class ClassificationSystem {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty criteria;

    public ClassificationSystem() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.criteria = new SimpleStringProperty();
    }

    public ClassificationSystem(int id, String name, String criteria) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.criteria = new SimpleStringProperty(criteria);
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

    public String getCriteria() {
        return criteria.get();
    }

    public StringProperty criteriaProperty() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria.set(criteria);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassificationSystem that = (ClassificationSystem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(criteria, that.criteria);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, criteria);
    }
}
