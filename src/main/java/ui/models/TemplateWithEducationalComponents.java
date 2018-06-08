package ui.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TemplateWithEducationalComponents {
    private ObjectProperty<EducationalComponentTemplate> educationalComponentTemplate;
    private ObservableList<EducationalComponentWithData> educationalComponents;

    public TemplateWithEducationalComponents() {
        this.educationalComponentTemplate = new SimpleObjectProperty<>();
        this.educationalComponents = new SimpleListProperty<>();
    }

    public TemplateWithEducationalComponents(EducationalComponentTemplate educationalComponentTemplate,
                                             List<EducationalComponentWithData> educationalComponents) {
        this.educationalComponentTemplate = new SimpleObjectProperty<>(educationalComponentTemplate);
        this.educationalComponents = FXCollections.observableArrayList();
        this.educationalComponents.setAll(educationalComponents);
    }

    public EducationalComponentTemplate getEducationalComponentTemplate() {
        return educationalComponentTemplate.get();
    }

    public void setEducationalComponentTemplate(EducationalComponentTemplate educationalComponentTemplate) {
        this.educationalComponentTemplate.set(educationalComponentTemplate);
    }

    public ObjectProperty<EducationalComponentTemplate> educationalComponentTemplateProperty() {
        return educationalComponentTemplate;
    }

    public ObservableList<EducationalComponentWithData> getEducationalComponents() {
        return educationalComponents;
    }

    public void setEducationalComponents(ObservableList<EducationalComponentWithData> educationalComponents) {
        this.educationalComponents = educationalComponents;
    }
}
