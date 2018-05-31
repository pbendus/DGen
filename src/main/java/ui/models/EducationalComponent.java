package ui.models;

import javafx.beans.property.*;

public class EducationalComponent {

    private IntegerProperty id;
    private IntegerProperty nationalScore;
    private DoubleProperty credits;
    private StringProperty courseTitle;
    private StringProperty educationalComponentType;
    private ObjectProperty<EducationalComponentTemplate> educationalComponentTemplate;
    private ObjectProperty<RatingPoint> ratingPoint;
    private ObjectProperty<NationalGrade> nationalGrade;
    private ObjectProperty<Diploma> diploma;

    public EducationalComponent() {
        this.id = new SimpleIntegerProperty();
        this.nationalScore = new SimpleIntegerProperty();
        this.credits = new SimpleDoubleProperty();
        this.courseTitle = new SimpleStringProperty();
        this.educationalComponentType = new SimpleStringProperty();
        this.educationalComponentTemplate = new SimpleObjectProperty<>();
        this.ratingPoint = new SimpleObjectProperty<>();
        this.nationalGrade = new SimpleObjectProperty<>();
        this.diploma = new SimpleObjectProperty<>();
    }

    public EducationalComponent(int id, int nationalScore, double credits, String courseTitle, String educationalComponentType,
                                EducationalComponentTemplate educationalComponentTemplate, RatingPoint ratingPoint,
                                NationalGrade nationalGrade, Diploma diploma) {
        this.id = new SimpleIntegerProperty(id);
        this.credits = new SimpleDoubleProperty(credits);
        this.courseTitle = new SimpleStringProperty(courseTitle);
        this.educationalComponentType = new SimpleStringProperty(educationalComponentType);
        this.nationalScore = new SimpleIntegerProperty(nationalScore);
        this.educationalComponentTemplate = new SimpleObjectProperty<>(educationalComponentTemplate);
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

    public int getNationalScore() {
        return nationalScore.get();
    }

    public IntegerProperty nationalScoreProperty() {
        return nationalScore;
    }

    public void setNationalScore(int nationalScore) {
        this.nationalScore.set(nationalScore);
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

    public String getEducationalComponentType() {
        return educationalComponentType.get();
    }

    public StringProperty educationalComponentTypeProperty() {
        return educationalComponentType;
    }

    public void setEducationalComponentType(String educationalComponentType) {
        this.educationalComponentType.set(educationalComponentType);
    }

    public EducationalComponentTemplate getEducationalComponentTemplate() {
        return educationalComponentTemplate.get();
    }

    public ObjectProperty<EducationalComponentTemplate> educationalComponentTemplateProperty() {
        return educationalComponentTemplate;
    }

    public void setEducationalComponentTemplate(
            EducationalComponentTemplate educationalComponentTemplate) {
        this.educationalComponentTemplate.set(educationalComponentTemplate);
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

    @Override
    public String toString() {
        return "EducationalComponent{" +
                "id=" + id +
                ", nationalScore=" + nationalScore +
                ", credits=" + credits +
                ", courseTitle=" + courseTitle +
                ", educationalComponentType=" + educationalComponentType +
                ", educationalComponentTemplate=" + educationalComponentTemplate +
                ", ratingPoint=" + ratingPoint +
                ", nationalGrade=" + nationalGrade +
                ", diploma=" + diploma +
                '}';
    }
}
