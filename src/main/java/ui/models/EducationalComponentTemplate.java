package ui.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EducationalComponentTemplate {
    private IntegerProperty id;
    private DoubleProperty credits;
    private StringProperty courseTitle;
    private ObjectProperty<EducationalComponentType> educationalComponentType;

    public EducationalComponentTemplate() {
        this.id = new SimpleIntegerProperty();
        this.credits = new SimpleDoubleProperty();
        this.courseTitle = new SimpleStringProperty();
        this.educationalComponentType = new SimpleObjectProperty<>();
    }

    public EducationalComponentTemplate(int id, double credit, String courseTitle,
                                        EducationalComponentType educationalComponentType, MainField mainField) {
        this.id = new SimpleIntegerProperty(id);
        this.credits = new SimpleDoubleProperty(credit);
        this.courseTitle = new SimpleStringProperty(courseTitle);
        this.educationalComponentType = new SimpleObjectProperty<>(educationalComponentType);
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

    public double getCredits() {
        return credits.get();
    }

    public DoubleProperty creditsProperty() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits.set(credits);
    }

    public String getCourseTitle() {
        return courseTitle.get();
    }

    public StringProperty courseTitleProperty() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle.set(courseTitle);
    }

    public EducationalComponentType getEducationalComponentType() {
        return educationalComponentType.get();
    }

    public ObjectProperty<EducationalComponentType> educationalComponentTypeProperty() {
        return educationalComponentType;
    }

    public void setEducationalComponentType(
            EducationalComponentType educationalComponentType) {
        this.educationalComponentType.set(educationalComponentType);
    }

    @Override
    public String toString() {
        return courseTitle.get();
    }
}
