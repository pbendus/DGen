package ui.models;

import javafx.beans.property.*;

public class EducationalComponent {

    private IntegerProperty id;
    private IntegerProperty credit;
    private StringProperty courseTitle;
    private IntegerProperty nationalScore;
    private ObjectProperty<EducationalComponentType> educationalComponentType;
    private ObjectProperty<RatingPoint> ratingPoint;
    private ObjectProperty<NationalGrade> nationalGrade;
    private ObjectProperty<Diploma> diploma;

    public EducationalComponent() {
    }

    public EducationalComponent(int id, int credit, String courseTitle, int nationalScore,
                                EducationalComponentType educationalComponentType, RatingPoint ratingPoint,
                                NationalGrade nationalGrade, Diploma diploma) {
        this.id = new SimpleIntegerProperty(id);
        this.credit = new SimpleIntegerProperty(credit);
        this.courseTitle = new SimpleStringProperty(courseTitle);
        this.nationalScore = new SimpleIntegerProperty(nationalScore);
        this.educationalComponentType = new SimpleObjectProperty<>(educationalComponentType);
        this.ratingPoint = new SimpleObjectProperty<>(ratingPoint);
        this.nationalGrade = new SimpleObjectProperty<>(nationalGrade);
        this.diploma = new SimpleObjectProperty<>(diploma);
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

    public int getNationalScore() {
        return nationalScore.get();
    }

    public IntegerProperty nationalScoreProperty() {
        return nationalScore;
    }

    public void setNationalScore(int nationalScore) {
        this.nationalScore.set(nationalScore);
    }

    public EducationalComponentType getEducationalComponentType() {
        return educationalComponentType.get();
    }

    public ObjectProperty<EducationalComponentType> educationalComponentTypeProperty() {
        return educationalComponentType;
    }

    public void setEducationalComponentType(EducationalComponentType educationalComponentType) {
        this.educationalComponentType.set(educationalComponentType);
    }

    public RatingPoint getRatingPoint() {
        return ratingPoint.get();
    }

    public ObjectProperty<RatingPoint> ratingPointProperty() {
        return ratingPoint;
    }

    public void setRatingPoint(RatingPoint ratingPoint) {
        this.ratingPoint.set(ratingPoint);
    }

    public NationalGrade getNationalGrade() {
        return nationalGrade.get();
    }

    public ObjectProperty<NationalGrade> nationalGradeProperty() {
        return nationalGrade;
    }

    public void setNationalGrade(NationalGrade nationalGrade) {
        this.nationalGrade.set(nationalGrade);
    }

    public Diploma getDiploma() {
        return diploma.get();
    }

    public ObjectProperty<Diploma> diplomaProperty() {
        return diploma;
    }

    public void setDiploma(Diploma diploma) {
        this.diploma.set(diploma);
    }
}
