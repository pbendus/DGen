package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class DiplomaMapper
    extends Mapper<db.entities.Diploma, ui.models.Diploma> {

  private StudentMapper studentMapper;
  private MainFieldMapper mainFieldMapper;
  private FieldOfStudyMapper fieldOfStudyMapper;
  private OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper;
  private AccessRequirementsMapper accessRequirementsMapper;
  private ModeOfStudyMapper modeOfStudyMapper;
  private ProfessionalStatusMapper professionalStatusMapper;
  private ClassificationSystemMapper classificationSystemMapper;
  private DurationOfTrainingMapper durationOfTrainingMapper;

  @Autowired
  public DiplomaMapper(StudentMapper studentMapper, MainFieldMapper mainFieldMapper,
      FieldOfStudyMapper fieldOfStudyMapper,
      OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper,
      AccessRequirementsMapper accessRequirementsMapper,
      ModeOfStudyMapper modeOfStudyMapper,
      ProfessionalStatusMapper professionalStatusMapper,
      ClassificationSystemMapper classificationSystemMapper,
      DurationOfTrainingMapper durationOfTrainingMapper) {
    this.studentMapper = studentMapper;
    this.mainFieldMapper = mainFieldMapper;
    this.fieldOfStudyMapper = fieldOfStudyMapper;
    this.officialDurationOfProgrammeMapper = officialDurationOfProgrammeMapper;
    this.accessRequirementsMapper = accessRequirementsMapper;
    this.modeOfStudyMapper = modeOfStudyMapper;
    this.professionalStatusMapper = professionalStatusMapper;
    this.classificationSystemMapper = classificationSystemMapper;
    this.durationOfTrainingMapper = durationOfTrainingMapper;
  }

  @Override public ui.models.Diploma map(db.entities.Diploma value) {
    final ui.models.Diploma diploma = new ui.models.Diploma();
    diploma.setId(value.getId());
    diploma.setNumber(value.getNumber());
    diploma.setRegistrationNumber(value.getRegistrationNumber());
    diploma.setDateOfIssue(value.getDateOfIssue());
    diploma.setStudent(studentMapper.map(value.getStudent()));
    diploma.setMainField(mainFieldMapper.map(value.getMainField()));
    diploma.setFieldOfStudy(fieldOfStudyMapper.map(value.getFieldOfStudy()));
    diploma.setOfficialDurationOfProgramme(
        officialDurationOfProgrammeMapper.map(value.getOfficialDurationOfProgramme()));
    diploma.setAccessRequirements(accessRequirementsMapper.map(value.getAccessRequirements()));
    diploma.setModeOfStudy(modeOfStudyMapper.map(value.getModeOfStudy()));
    diploma.setProfessionalStatus(professionalStatusMapper.map(value.getProfessionalStatus()));
    diploma.setClassificationSystem(
        classificationSystemMapper.map(value.getClassificationSystem()));
    diploma.setDurationOfTraining(durationOfTrainingMapper.map(value.getDurationOfTraining()));

    return diploma;
  }

  @Override
  public db.entities.Diploma reverseMap(ui.models.Diploma value) {
    final db.entities.Diploma diploma = new db.entities.Diploma();
    diploma.setId(value.getId());
    diploma.setNumber(value.getNumber());
    diploma.setRegistrationNumber(value.getRegistrationNumber());
    diploma.setDateOfIssue(value.getDateOfIssue());
    diploma.setStudent(studentMapper.reverseMap(value.getStudent()));
    diploma.setMainField(mainFieldMapper.reverseMap(value.getMainField()));
    diploma.setFieldOfStudy(fieldOfStudyMapper.reverseMap(value.getFieldOfStudy()));
    diploma.setOfficialDurationOfProgramme(
        officialDurationOfProgrammeMapper.reverseMap(value.getOfficialDurationOfProgramme()));
    diploma.setAccessRequirements(
        accessRequirementsMapper.reverseMap(value.getAccessRequirements()));
    diploma.setModeOfStudy(modeOfStudyMapper.reverseMap(value.getModeOfStudy()));
    diploma.setProfessionalStatus(
        professionalStatusMapper.reverseMap(value.getProfessionalStatus()));
    diploma.setClassificationSystem(
        classificationSystemMapper.reverseMap(value.getClassificationSystem()));
    diploma.setDurationOfTraining(
        durationOfTrainingMapper.reverseMap(value.getDurationOfTraining()));

    return diploma;
  }
}
