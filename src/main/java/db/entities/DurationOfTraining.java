package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "duration_of_training")
public class DurationOfTraining {

  @DatabaseField(id = true, generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
  private String name;

  @DatabaseField(canBeNull = false, useGetSet = true, columnName = "mode_of_study_id", foreign = true,
      foreignAutoCreate = true, foreignAutoRefresh = true)
  private ModeOfStudy modeOfStudy;

  public DurationOfTraining() {
  }

  public DurationOfTraining(int id, String name, ModeOfStudy modeOfStudy) {
    this.id = id;
    this.name = name;
    this.modeOfStudy = modeOfStudy;
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

  public ModeOfStudy getModeOfStudy() {
    return modeOfStudy;
  }

  public void setModeOfStudy(ModeOfStudy modeOfStudy) {
    this.modeOfStudy = modeOfStudy;
  }
}
