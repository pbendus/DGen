package ui.models;

import javafx.beans.property.*;

public class StudentWithAVG implements Comparable<StudentWithAVG> {
    private IntegerProperty id;
    private ObjectProperty<Diploma> diploma;
    private SimpleDoubleProperty avg;
    private LongProperty numberOfFive;
    private LongProperty numberOfFour;
    private LongProperty numberOfThree;
    private LongProperty numberOfEducationalComponent;

    public StudentWithAVG() {
        this.id = new SimpleIntegerProperty();
        this.diploma = new SimpleObjectProperty<>();
        this.avg = new SimpleDoubleProperty();
        this.numberOfFive = new SimpleLongProperty();
        this.numberOfFour = new SimpleLongProperty();
        this.numberOfThree = new SimpleLongProperty();
        this.numberOfEducationalComponent = new SimpleLongProperty();
    }

    public StudentWithAVG(Diploma diploma, double avg, long numberOfFive, long numberOfFour, long numberOfThree,
                          long numberOfEducationalComponent) {
        this.id = new SimpleIntegerProperty();
        this.diploma = new SimpleObjectProperty<>(diploma);
        this.avg = new SimpleDoubleProperty(avg);
        this.numberOfFive = new SimpleLongProperty(numberOfFive);
        this.numberOfFour = new SimpleLongProperty(numberOfFour);
        this.numberOfThree = new SimpleLongProperty(numberOfThree);
        this.numberOfEducationalComponent = new SimpleLongProperty(numberOfEducationalComponent);
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

    public Diploma getDiploma() {
        return diploma.get();
    }

    public void setDiploma(Diploma diploma) {
        this.diploma.set(diploma);
    }

    public ObjectProperty<Diploma> diplomaProperty() {
        return diploma;
    }

    public double getAvg() {
        return avg.get();
    }

    public void setAvg(double avg) {
        this.avg.set(avg);
    }

    public SimpleDoubleProperty avgProperty() {
        return avg;
    }

    public long getNumberOfFive() {
        return numberOfFive.get();
    }

    public void setNumberOfFive(long numberOfFive) {
        this.numberOfFive.set(numberOfFive);
    }

    public LongProperty numberOfFiveProperty() {
        return numberOfFive;
    }

    public long getNumberOfFour() {
        return numberOfFour.get();
    }

    public void setNumberOfFour(long numberOfFour) {
        this.numberOfFour.set(numberOfFour);
    }

    public LongProperty numberOfFourProperty() {
        return numberOfFour;
    }

    public long getNumberOfThree() {
        return numberOfThree.get();
    }

    public void setNumberOfThree(long numberOfThree) {
        this.numberOfThree.set(numberOfThree);
    }

    public LongProperty numberOfThreeProperty() {
        return numberOfThree;
    }

    public long getNumberOfEducationalComponent() {
        return numberOfEducationalComponent.get();
    }

    public void setNumberOfEducationalComponent(long numberOfEducationalComponent) {
        this.numberOfEducationalComponent.set(numberOfEducationalComponent);
    }

    public LongProperty numberOfEducationalComponentProperty() {
        return numberOfEducationalComponent;
    }

    @Override
    public int compareTo(StudentWithAVG o) {
        return ((this.getAvg() == o.getAvg()) ?
                (((this.getNumberOfThree() - o.getNumberOfThree() == 0) ?
                        (this.getNumberOfFour() > o.getNumberOfFour() ? -1 : 1) :
                        (this.getNumberOfFour() > o.getNumberOfFour() ? -1 : 1)))
                : (this.getAvg() > o.getAvg() ? -1 : 1));
    }
}
