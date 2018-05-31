package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.OfficialDurationOfProgramme;

@Service
public class OfficialDurationOfProgrammeMapper
    extends Mapper<db.entities.OfficialDurationOfProgramme, OfficialDurationOfProgramme> {

  private ModeOfStudyMapper modeOfStudyMapper;
  private DurationOfStudyMapper durationOfStudyMapper;

  @Autowired
  public OfficialDurationOfProgrammeMapper(ModeOfStudyMapper modeOfStudyMapper,
      DurationOfStudyMapper durationOfStudyMapper) {
    this.modeOfStudyMapper = modeOfStudyMapper;
    this.durationOfStudyMapper = durationOfStudyMapper;
  }

  @Override public OfficialDurationOfProgramme map(db.entities.OfficialDurationOfProgramme value) {
    final OfficialDurationOfProgramme officialDurationOfProgramme =
        new OfficialDurationOfProgramme();
    officialDurationOfProgramme.setId(value.getId());
    officialDurationOfProgramme.setName(value.getName());
    officialDurationOfProgramme.setModeOfStudy(modeOfStudyMapper.map(value.getModeOfStudy()));
    officialDurationOfProgramme.setDurationOfStudy(
        durationOfStudyMapper.map(value.getDurationOfStudy()));
    return officialDurationOfProgramme;
  }

  @Override
  public db.entities.OfficialDurationOfProgramme reverseMap(OfficialDurationOfProgramme value) {
    final db.entities.OfficialDurationOfProgramme officialDurationOfProgramme =
        new db.entities.OfficialDurationOfProgramme();
    officialDurationOfProgramme.setId(value.getId());
    officialDurationOfProgramme.setName(value.getName());
    officialDurationOfProgramme.setModeOfStudy(
        modeOfStudyMapper.reverseMap(value.getModeOfStudy()));
    officialDurationOfProgramme.setDurationOfStudy(
        durationOfStudyMapper.reverseMap(value.getDurationOfStudy()));
    return officialDurationOfProgramme;
  }
}
