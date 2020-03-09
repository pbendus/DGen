package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "official_duration_of_programme")
public class OfficialDurationOfProgramme {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
    private String name;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "mode_of_study_id", foreign = true,
            foreignAutoCreate = true, foreignAutoRefresh = true)
    private ModeOfStudy modeOfStudy;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "duration_of_study_id", foreign = true,
            foreignAutoCreate = true, foreignAutoRefresh = true)
    private DurationOfStudy durationOfStudy;

    public OfficialDurationOfProgramme() {
    }

    public OfficialDurationOfProgramme(int id, String name, ModeOfStudy modeOfStudy,
                                       DurationOfStudy durationOfStudy) {
        this.id = id;
        this.name = name;
        this.modeOfStudy = modeOfStudy;
        this.durationOfStudy = durationOfStudy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DurationOfStudy getDurationOfStudy() {
        return durationOfStudy;
    }

    public void setDurationOfStudy(DurationOfStudy durationOfStudy) {
        this.durationOfStudy = durationOfStudy;
    }

    public ModeOfStudy getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(ModeOfStudy modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
    }
}
