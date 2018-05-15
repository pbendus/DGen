package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "educational_component")
public class EducationalComponent {

  @DatabaseField(generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "national_score")
  private int nationalScore;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "educational_component_template_id",
      foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
  private EducationalComponentTemplate educationalComponentTemplate;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "rating_point_id",
      foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
  private RatingPoint ratingPoint;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "national_grade_id",
      foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
  private NationalGrade nationalGrade;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "diploma_id",
      foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
  private Diploma diploma;

  public EducationalComponent() {
  }

  public EducationalComponent(int id, int nationalScore,
      EducationalComponentTemplate educationalComponentTemplate, RatingPoint ratingPoint,
      NationalGrade nationalGrade, Diploma diploma) {
    this.id = id;
    this.nationalScore = nationalScore;
    this.educationalComponentTemplate = educationalComponentTemplate;
    this.ratingPoint = ratingPoint;
    this.nationalGrade = nationalGrade;
    this.diploma = diploma;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNationalScore() {
    return nationalScore;
  }

  public void setNationalScore(int nationalScore) {
    this.nationalScore = nationalScore;
  }

  public EducationalComponentTemplate getEducationalComponentTemplate() {
    return educationalComponentTemplate;
  }

  public void setEducationalComponentTemplate(
      EducationalComponentTemplate educationalComponentTemplate) {
    this.educationalComponentTemplate = educationalComponentTemplate;
  }

  public RatingPoint getRatingPoint() {
    return ratingPoint;
  }

  public void setRatingPoint(RatingPoint ratingPoint) {
    this.ratingPoint = ratingPoint;
  }

  public NationalGrade getNationalGrade() {
    return nationalGrade;
  }

  public void setNationalGrade(NationalGrade nationalGrade) {
    this.nationalGrade = nationalGrade;
  }

  public Diploma getDiploma() {
    return diploma;
  }

  public void setDiploma(Diploma diploma) {
    this.diploma = diploma;
  }
}
