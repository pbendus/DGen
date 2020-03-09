package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "national_grade")
public class NationalGrade {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
    private String name;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "min_national_score")
    private int minNationalScore;

    @DatabaseField(canBeNull = false, useGetSet = true, columnName = "max_national_score")
    private int maxNationalScore;

    public NationalGrade() {
    }

    public NationalGrade(int id, String name, int minNationalScore, int maxNationalScore) {
        this.id = id;
        this.name = name;
        this.minNationalScore = minNationalScore;
        this.maxNationalScore = maxNationalScore;
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

    public int getMinNationalScore() {
        return minNationalScore;
    }

    public void setMinNationalScore(int minNationalScore) {
        this.minNationalScore = minNationalScore;
    }

    public int getMaxNationalScore() {
        return maxNationalScore;
    }

    public void setMaxNationalScore(int maxNationalScore) {
        this.maxNationalScore = maxNationalScore;
    }
}
