package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "diploma_subject")
public class DiplomaSubject {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "subject_uk")
    private String subjectUK;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "subject_en")
    private String subjectEN;

    public DiplomaSubject() {
    }

    public DiplomaSubject(int id, String subjectUK, String subjectEN) {
        this.id = id;
        this.subjectUK = subjectUK;
        this.subjectEN = subjectEN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectUK() {
        return subjectUK;
    }

    public void setSubjectUK(String subjectUK) {
        this.subjectUK = subjectUK;
    }

    public String getSubjectEN() {
        return subjectEN;
    }

    public void setSubjectEN(String subjectEN) {
        this.subjectEN = subjectEN;
    }
}
