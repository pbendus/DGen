package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.Protocol;

@Service
public class ProtocolMapper
    extends Mapper<db.entities.Protocol, Protocol> {

  @Autowired
  public ProtocolMapper() {
  }

  @Override public Protocol map(db.entities.Protocol value) {
    final Protocol previousDocument = new Protocol();
    previousDocument.setId(value.getId());
    previousDocument.setNameEN(value.getNameEN());
    previousDocument.setNameUK(value.getNameUK());
    return previousDocument;
  }

  @Override public db.entities.Protocol reverseMap(Protocol value) {
    final db.entities.Protocol previousDocument = new db.entities.Protocol();
    previousDocument.setId(value.getId());
    previousDocument.setNameEN(value.getNameEN());
    previousDocument.setNameUK(value.getNameUK());
    return previousDocument;
  }
}
