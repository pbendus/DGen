package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.Student;

@Service
public class StudentMapper
    extends Mapper<db.entities.Student, Student> {

  private ProtocolMapper protocolMapper;
  private PreviousDocumentMapper previousDocumentMapper;
  private ModeOfStudyMapper modeOfStudyMapper;
  private DurationOfStudyMapper durationOfStudyMapper;
  private GroupMapper groupMapper;

  @Autowired
  public StudentMapper(ProtocolMapper protocolMapper,
      PreviousDocumentMapper previousDocumentMapper, ModeOfStudyMapper modeOfStudyMapper,
      DurationOfStudyMapper durationOfStudyMapper, GroupMapper groupMapper) {
    this.protocolMapper = protocolMapper;
    this.previousDocumentMapper = previousDocumentMapper;
    this.modeOfStudyMapper = modeOfStudyMapper;
    this.durationOfStudyMapper = durationOfStudyMapper;
    this.groupMapper = groupMapper;
  }

  @Override public Student map(db.entities.Student value) {
    final Student student = new Student();
    student.setId(value.getId());
    student.setFamilyName(value.getFamilyName());
    student.setFamilyNameTr(value.getFamilyNameTr());
    student.setGivenName(value.getGivenName());
    student.setGivenNameTr(value.getGivenNameTr());
    student.setDateOfBirth(value.getDateOfBirth());
    student.setProtocol(protocolMapper.map(value.getProtocol()));
    student.setPreviousDocument(previousDocumentMapper.map(value.getPreviousDocument()));
    student.setModeOfStudyObject(modeOfStudyMapper.map(value.getModeOfStudy()));
    student.setDurationOfStudy(durationOfStudyMapper.map(value.getDurationOfStudy()));
    student.setGroup(groupMapper.map(value.getGroup()));
    return student;
  }

  @Override public db.entities.Student reverseMap(Student value) {
    final db.entities.Student student = new db.entities.Student();
    student.setId(value.getId());
    student.setFamilyName(value.getFamilyName());
    student.setFamilyNameTr(value.getFamilyNameTr());
    student.setGivenName(value.getGivenName());
    student.setGivenNameTr(value.getGivenNameTr());
    student.setDateOfBirth(value.getDateOfBirth());
    student.setProtocol(protocolMapper.reverseMap(value.getProtocol()));
    student.setPreviousDocument(previousDocumentMapper.reverseMap(value.getPreviousDocument()));
    student.setDurationOfStudy(durationOfStudyMapper.reverseMap(value.getDurationOfStudy()));
    student.setGroup(groupMapper.reverseMap(value.getGroup()));
    return student;
  }
}
