package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ar_dos")
public class ArDos {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "access_requirements_id", foreign = true,
            foreignAutoCreate = true, foreignAutoRefresh = true)
    private AccessRequirements accessRequirements;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "duration_of_study_id", foreign = true,
            foreignAutoCreate = true, foreignAutoRefresh = true)
    private DurationOfStudy durationOfStudy;

    public ArDos() {
    }

    public ArDos(int id, AccessRequirements accessRequirements, DurationOfStudy durationOfStudy) {
        this.id = id;
        this.accessRequirements = accessRequirements;
        this.durationOfStudy = durationOfStudy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccessRequirements getAccessRequirements() {
        return accessRequirements;
    }

    public void setAccessRequirements(AccessRequirements accessRequirements) {
        this.accessRequirements = accessRequirements;
    }

    public DurationOfStudy getDurationOfStudy() {
        return durationOfStudy;
    }

    public void setDurationOfStudy(DurationOfStudy durationOfStudy) {
        this.durationOfStudy = durationOfStudy;
    }

    @Override
    public String toString() {
        return "ArDos{" +
                "id=" + id +
                ", accessRequirements=" + accessRequirements +
                ", durationOfStudy=" + durationOfStudy +
                '}';
    }
}
