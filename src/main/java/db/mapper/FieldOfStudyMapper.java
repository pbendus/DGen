package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.FieldOfStudy;

@Service
public class FieldOfStudyMapper
    extends Mapper<db.entities.FieldOfStudy, FieldOfStudy> {

  @Autowired
  public FieldOfStudyMapper() {
  }

  @Override public FieldOfStudy map(db.entities.FieldOfStudy value) {
    final FieldOfStudy fieldOfStudy = new FieldOfStudy();
    fieldOfStudy.setId(value.getId());
    fieldOfStudy.setName(value.getName());
    return fieldOfStudy;
  }

  @Override public db.entities.FieldOfStudy reverseMap(FieldOfStudy value) {
    final db.entities.FieldOfStudy fieldOfStudy = new db.entities.FieldOfStudy();
    fieldOfStudy.setId(value.getId());
    fieldOfStudy.setName(value.getName());
    return fieldOfStudy;
  }
}
