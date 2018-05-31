package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EctsCreditsMapper
    extends Mapper<db.entities.EctsCredits, ui.models.EctsCredits> {

  private DurationOfStudyMapper durationOfStudyMapper;

  @Autowired
  public EctsCreditsMapper(DurationOfStudyMapper durationOfStudyMapper) {
    this.durationOfStudyMapper = durationOfStudyMapper;
  }

  @Override public ui.models.EctsCredits map(db.entities.EctsCredits value) {
    final ui.models.EctsCredits ectsCredits = new ui.models.EctsCredits();
    ectsCredits.setId(value.getId());
    ectsCredits.setName(value.getName());
    ectsCredits.setDurationOfStudy(durationOfStudyMapper.map(value.getDurationOfStudy()));
    return ectsCredits;
  }

  @Override public db.entities.EctsCredits reverseMap(ui.models.EctsCredits value) {
    final db.entities.EctsCredits ectsCredits = new db.entities.EctsCredits();
    ectsCredits.setId(value.getId());
    ectsCredits.setName(value.getName());
    ectsCredits.setDurationOfStudy(durationOfStudyMapper.reverseMap(value.getDurationOfStudy()));
    return ectsCredits;
  }
}
