package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NationalGrade {

    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty minNationalScore;
    private IntegerProperty maxNationalScore;

    public NationalGrade() {
    }

    public NationalGrade(int id, String name, int minNationalScore, int maxNationalScore) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.minNationalScore = new SimpleIntegerProperty(minNationalScore);
        this.maxNationalScore = new SimpleIntegerProperty(maxNationalScore);
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

    public int getMinNationalScore() {
        return minNationalScore.get();
    }

    public IntegerProperty minNationalScoreProperty() {
        return minNationalScore;
    }

    public void setMinNationalScore(int minNationalScore) {
        this.minNationalScore.set(minNationalScore);
    }

    public int getMaxNationalScore() {
        return maxNationalScore.get();
    }

    public IntegerProperty maxNationalScoreProperty() {
        return maxNationalScore;
    }

    public void setMaxNationalScore(int maxNationalScore) {
        this.maxNationalScore.set(maxNationalScore);
    }
}
