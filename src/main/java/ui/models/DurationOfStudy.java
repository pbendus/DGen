package ui.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DurationOfStudy {
  private IntegerProperty id;
  private StringProperty value;

  public DurationOfStudy() {
    this.id = new SimpleIntegerProperty();
    this.value = new SimpleStringProperty();
  }

  public DurationOfStudy(int id, String value) {
    this.id = new SimpleIntegerProperty(id);
    this.value = new SimpleStringProperty(value);
  }

  public int getId() {
    return id.get();
  }

  public IntegerProperty idProperty() {
    return id;
  }

  public void setId(int id) {
    this.id.set(id);
  }

  public String getValue() {
    return value.get();
  }

  public StringProperty nameProperty() {
    return value;
  }

  public void setValue(String name) {
    this.value.set(name);
  }

  @Override
  public String toString() {
    return value.get();
  }
}
