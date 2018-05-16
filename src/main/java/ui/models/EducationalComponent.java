package ui.models;

import javafx.beans.property.*;

public class EducationalComponent {

  private IntegerProperty id;
  private IntegerProperty nationalScore;
  private ObjectProperty<EducationalComponentTemplate> educationalComponentTemplate;
  private ObjectProperty<RatingPoint> ratingPoint;
  private ObjectProperty<NationalGrade> nationalGrade;
  private ObjectProperty<Diploma> diploma;

  public EducationalComponent() {
  }

  public EducationalComponent(int id, int nationalScore,
      EducationalComponentTemplate educationalComponentTemplate, RatingPoint ratingPoint,
      NationalGrade nationalGrade, Diploma diploma) {
    this.id = new SimpleIntegerProperty(id);
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
}
