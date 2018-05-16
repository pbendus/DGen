package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DurationOfTrainingMapper
    extends Mapper<db.entities.DurationOfTraining, ui.models.DurationOfTraining> {

  private ModeOfStudyMapper modeOfStudyMapper;

  @Autowired
  public DurationOfTrainingMapper(ModeOfStudyMapper modeOfStudyMapper) {
    this.modeOfStudyMapper = modeOfStudyMapper;
  }

  @Override public ui.models.DurationOfTraining map(db.entities.DurationOfTraining value) {
    final ui.models.DurationOfTraining diplomaSubject = new ui.models.DurationOfTraining();
    diplomaSubject.setId(value.getId());
    diplomaSubject.setName(value.getName());
    diplomaSubject.setModeOfStudy(modeOfStudyMapper.map(value.getModeOfStudy()));

    return diplomaSubject;
  }

  @Override
  public db.entities.DurationOfTraining reverseMap(ui.models.DurationOfTraining value) {
    final db.entities.DurationOfTraining diplomaSubject = new db.entities.DurationOfTraining();
    diplomaSubject.setId(value.getId());
    diplomaSubject.setName(value.getName());
    diplomaSubject.setModeOfStudy(modeOfStudyMapper.reverseMap(value.getModeOfStudy()));

    return diplomaSubject;
  }
}
