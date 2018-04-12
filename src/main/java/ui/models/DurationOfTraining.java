package ui.models;

import javafx.beans.property.*;

public class DurationOfTraining {

    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<ModeOfStudy> modeOfStudy;

    public DurationOfTraining() {
    }

    public DurationOfTraining(int id, String name, ModeOfStudy modeOfStudy) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.modeOfStudy = new SimpleObjectProperty<>(modeOfStudy);
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

    public ModeOfStudy getModeOfStudy() {
        return modeOfStudy.get();
    }

    public ObjectProperty<ModeOfStudy> modeOfStudyProperty() {
        return modeOfStudy;
    }

    public void setModeOfStudy(ModeOfStudy modeOfStudy) {
        this.modeOfStudy.set(modeOfStudy);
    }
}
