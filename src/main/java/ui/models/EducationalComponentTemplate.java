package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EducationalComponentTemplate {
    private IntegerProperty id;
    private IntegerProperty credit;
    private StringProperty courseTitle;
    private ObjectProperty<EducationalComponentType> educationalComponentType;
    private ObjectProperty<MainField> mainField;

    public EducationalComponentTemplate() {
        this.id = new SimpleIntegerProperty();
        this.credit = new SimpleIntegerProperty();
        this.courseTitle = new SimpleStringProperty();
        this.educationalComponentType = new SimpleObjectProperty<>();
        this.mainField = new SimpleObjectProperty<>();
    }

    public EducationalComponentTemplate(int id, int credit, String courseTitle,
                                        EducationalComponentType educationalComponentType, MainField mainField) {
        this.id = new SimpleIntegerProperty(id);
        this.credit = new SimpleIntegerProperty(credit);
        this.courseTitle = new SimpleStringProperty(courseTitle);
        this.educationalComponentType = new SimpleObjectProperty<>(educationalComponentType);
        this.mainField = new SimpleObjectProperty<>(mainField);
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

    public int getCredit() {
        return credit.get();
    }

    public IntegerProperty creditProperty() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit.set(credit);
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

    public MainField getMainField() {
        return mainField.get();
    }

    public ObjectProperty<MainField> mainFieldProperty() {
        return mainField;
    }

    public void setMainField(MainField mainField) {
        this.mainField.set(mainField);
    }

    @Override
    public String toString() {
        return courseTitle.get();
    }
}
