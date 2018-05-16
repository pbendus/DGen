package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.MainField;

@Service
public class MainFieldMapper
    extends Mapper<db.entities.MainField, MainField> {

  @Autowired
  public MainFieldMapper() {
  }

  @Override public MainField map(db.entities.MainField value) {
    final MainField mainField = new MainField();
    mainField.setId(value.getId());
    mainField.setName(value.getName());
    return mainField;
  }

  @Override public db.entities.MainField reverseMap(MainField value) {
    final db.entities.MainField mainField = new db.entities.MainField();
    mainField.setId(value.getId());
    mainField.setName(value.getName());
    return mainField;
  }
}
