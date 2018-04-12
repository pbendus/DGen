package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "classification_system")
public class ClassificationSystem {

  @DatabaseField(id = true, generatedId = true, useGetSet = true)
  private int id;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
  private String value;

  @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
  private String criteria;

  public ClassificationSystem() {
  }

  public ClassificationSystem(int id, String value, String criteria) {
    this.id = id;
    this.value = value;
    this.criteria = criteria;
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

  public void setValue(String value) {
    this.value = value;
  }

  public String getCriteria() {
    return criteria;
  }

  public void setCriteria(String criteria) {
    this.criteria = criteria;
  }
}
