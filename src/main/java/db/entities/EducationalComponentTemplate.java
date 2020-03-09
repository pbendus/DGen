package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "educational_component_template")
public class EducationalComponentTemplate {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true)
    private double credits;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "course_title")
    private String courseTitle;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "educational_component_type_id",
            foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private EducationalComponentType educationalComponentType;

    public EducationalComponentTemplate() {
    }

    public EducationalComponentTemplate(int id, double credits, String courseTitle,
                                        EducationalComponentType educationalComponentType) {
        this.id = id;
        this.credits = credits;
        this.courseTitle = courseTitle;
        this.educationalComponentType = educationalComponentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
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
}
