package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.ProfessionalStatus;

@Service
public class ProfessionalStatusMapper
    extends Mapper<db.entities.ProfessionalStatus, ProfessionalStatus> {

  @Autowired
  public ProfessionalStatusMapper() {
  }

  @Override public ProfessionalStatus map(db.entities.ProfessionalStatus value) {
    final ProfessionalStatus previousDocument = new ProfessionalStatus();
    previousDocument.setId(value.getId());
    previousDocument.setName(value.getName());
    return previousDocument;
  }

  @Override public db.entities.ProfessionalStatus reverseMap(ProfessionalStatus value) {
    final db.entities.ProfessionalStatus previousDocument = new db.entities.ProfessionalStatus();
    previousDocument.setId(value.getId());
    previousDocument.setName(value.getName());
    return previousDocument;
  }
}
