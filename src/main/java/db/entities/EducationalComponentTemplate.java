package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "educational_component_template")
public class EducationalComponentTemplate {

  @DatabaseField(generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true)
  private int credits;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "course_title")
  private String courseTitle;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "educational_component_type_id",
      foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
  private EducationalComponentType educationalComponentType;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "main_field_id",
      foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
  private MainField mainField;

  public EducationalComponentTemplate() {
  }

  public EducationalComponentTemplate(int id, int credits, String courseTitle,
      EducationalComponentType educationalComponentType, MainField mainField) {
    this.id = id;
    this.credits = credits;
    this.courseTitle = courseTitle;
    this.educationalComponentType = educationalComponentType;
    this.mainField = mainField;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }

  public EducationalComponentType getEducationalComponentType() {
    return educationalComponentType;
  }

  public void setEducationalComponentType(EducationalComponentType educationalComponentType) {
    this.educationalComponentType = educationalComponentType;
  }

  public MainField getMainField() {
    return mainField;
  }

  public void setMainField(MainField mainField) {
    this.mainField = mainField;
  }
}
