package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "duration_of_study")
public class DurationOfStudy {

  @DatabaseField(generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
  private String value;

  public DurationOfStudy() {
  }

  public DurationOfStudy(int id, String value) {
    this.id = id;
    this.value = value;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String name) {
    this.value = name;
  }
}
