package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EctsCredits {

    private IntegerProperty id;
    private StringProperty name;
    private SimpleObjectProperty<DurationOfStudy> durationOfStudy;

    public EctsCredits() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.durationOfStudy = new SimpleObjectProperty<>();
    }

    public EctsCredits(int id, String name,
        DurationOfStudy durationOfStudy) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.durationOfStudy = new SimpleObjectProperty<>(durationOfStudy);
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

    public DurationOfStudy getDurationOfStudy() {
        return durationOfStudy.get();
    }

    public SimpleObjectProperty<DurationOfStudy> durationOfStudyProperty() {
        return durationOfStudy;
    }

    public void setDurationOfStudy(DurationOfStudy durationOfStudy) {
        this.durationOfStudy.set(durationOfStudy);
    }
}
