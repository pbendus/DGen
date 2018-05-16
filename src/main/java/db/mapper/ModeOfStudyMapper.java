package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.ModeOfStudy;

@Service
public class ModeOfStudyMapper
    extends Mapper<db.entities.ModeOfStudy, ModeOfStudy> {

  @Autowired
  public ModeOfStudyMapper() {
  }

  @Override public ModeOfStudy map(db.entities.ModeOfStudy value) {
    final ModeOfStudy modeOfStudy = new ModeOfStudy();
    modeOfStudy.setId(value.getId());
    modeOfStudy.setName(value.getName());
    return modeOfStudy;
  }

  @Override public db.entities.ModeOfStudy reverseMap(ModeOfStudy value) {
    final db.entities.ModeOfStudy modeOfStudy = new db.entities.ModeOfStudy();
    modeOfStudy.setId(value.getId());
    modeOfStudy.setName(value.getName());
    return modeOfStudy;
  }
}
