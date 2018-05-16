package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.OfficialDurationOfProgramme;

@Service
public class OfficialDurationOfProgrammeMapper
    extends Mapper<db.entities.OfficialDurationOfProgramme, OfficialDurationOfProgramme> {

  @Autowired
  public OfficialDurationOfProgrammeMapper() {
  }

  @Override public OfficialDurationOfProgramme map(db.entities.OfficialDurationOfProgramme value) {
    final OfficialDurationOfProgramme officialDurationOfProgramme =
        new OfficialDurationOfProgramme();
    officialDurationOfProgramme.setId(value.getId());
    officialDurationOfProgramme.setName(value.getName());
    return officialDurationOfProgramme;
  }

  @Override
  public db.entities.OfficialDurationOfProgramme reverseMap(OfficialDurationOfProgramme value) {
    final db.entities.OfficialDurationOfProgramme officialDurationOfProgramme =
        new db.entities.OfficialDurationOfProgramme();
    officialDurationOfProgramme.setId(value.getId());
    officialDurationOfProgramme.setName(value.getName());
    return officialDurationOfProgramme;
  }
}
