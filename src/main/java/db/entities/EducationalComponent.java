package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "educational_component")
public class EducationalComponent {

  @DatabaseField(id = true, generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
  private int credit;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true, columnName = "course_title")
  private String courseTitle;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true, columnName = "national_score")
  private int nationalScore;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "educational_component_type_id",
      foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
  private EducationalComponentType educationalComponentType;

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

  public EducationalComponent(int id, int credit, String courseTitle, int nationalScore,
      EducationalComponentType educationalComponentType, RatingPoint ratingPoint,
      NationalGrade nationalGrade, Diploma diploma) {
    this.id = id;
    this.credit = credit;
    this.courseTitle = courseTitle;
    this.nationalScore = nationalScore;
    this.educationalComponentType = educationalComponentType;
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

  public int getCredit() {
    return credit;
  }

  public void setCredit(int credit) {
    this.credit = credit;
  }

  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }

  public int getNationalScore() {
    return nationalScore;
  }

  public void setNationalScore(int nationalScore) {
    this.nationalScore = nationalScore;
  }

  public EducationalComponentType getEducationalComponentType() {
    return educationalComponentType;
  }

  public void setEducationalComponentType(EducationalComponentType educationalComponentType) {
    this.educationalComponentType = educationalComponentType;
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
