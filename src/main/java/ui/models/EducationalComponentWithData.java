package ui.models;

import javafx.beans.property.*;

public class EducationalComponentWithData {

    private IntegerProperty id;
    private StringProperty group;
    private StringProperty student;
    private ObjectProperty<EducationalComponent> educationalComponent;
    private IntegerProperty nationalScore;

    public EducationalComponentWithData() {
        this.group = new SimpleStringProperty();
        this.student = new SimpleStringProperty();
        this.educationalComponent = new SimpleObjectProperty<>();
        this.nationalScore = new SimpleIntegerProperty();
        this.id = new SimpleIntegerProperty();
    }

    public EducationalComponentWithData(Group group, Student student, EducationalComponent educationalComponent,
                                        int nationalScore) {
        this.id = new SimpleIntegerProperty(educationalComponent.getId());
        this.group = new SimpleStringProperty(group.getName());
        this.student = new SimpleStringProperty(student.fullNameProperty().get());
        this.educationalComponent = new SimpleObjectProperty<>(educationalComponent);
        this.nationalScore = new SimpleIntegerProperty(nationalScore);
    }

    public String getGroup() {
        return group.get();
    }

    public void setGroup(Group group) {
        this.group.set(group.getName());
    }

    public StringProperty groupProperty() {
        return group;
    }

    public String getStudent() {
        return student.get();
    }

    public void setStudent(Student student) {
        this.student.set(student.fullNameProperty().get());
    }

    public StringProperty studentProperty() {
        return student;
    }

    public EducationalComponent getEducationalComponent() {
        return educationalComponent.get();
    }

    public void setEducationalComponent(EducationalComponent educationalComponent) {
        this.educationalComponent.set(educationalComponent);
        this.nationalScore.setValue(educationalComponent.getNationalScore());
        this.id.setValue(educationalComponent.getId());
    }

    public ObjectProperty<EducationalComponent> educationalComponentProperty() {
        return educationalComponent;
    }

    public int getNationalScore() {
        return nationalScore.get();
    }

    public void setNationalScore(int nationalScore) {
        this.nationalScore.set(nationalScore);
    }

    public IntegerProperty nationalScoreProperty() {
        return nationalScore;
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
}
