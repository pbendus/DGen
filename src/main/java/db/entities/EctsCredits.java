package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ects_credits")
public class EctsCredits {

  @DatabaseField(generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
  private String name;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "duration_of_study_id",
      foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
  private DurationOfStudy durationOfStudy;

  public EctsCredits() {
  }

  public EctsCredits(int id, String name, DurationOfStudy durationOfStudy) {
    this.id = id;
    this.name = name;
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
}
