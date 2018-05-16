package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.Student;

@Service
public class StudentMapper
    extends Mapper<db.entities.Student, Student> {

  private ProtocolMapper protocolMapper;
  private DiplomaSubjectMapper diplomaSubjectMapper;
  private PreviousDocumentMapper previousDocumentMapper;

  @Autowired
  public StudentMapper(ProtocolMapper protocolMapper,
      DiplomaSubjectMapper diplomaSubjectMapper,
      PreviousDocumentMapper previousDocumentMapper) {
    this.protocolMapper = protocolMapper;
    this.diplomaSubjectMapper = diplomaSubjectMapper;
    this.previousDocumentMapper = previousDocumentMapper;
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
    student.setDiplomaSubject(diplomaSubjectMapper.map(value.getDiplomaSubject()));
    student.setPreviousDocument(previousDocumentMapper.map(value.getPreviousDocument()));
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
    student.setDiplomaSubject(diplomaSubjectMapper.reverseMap(value.getDiplomaSubject()));
    student.setPreviousDocument(previousDocumentMapper.reverseMap(value.getPreviousDocument()));
    return student;
  }
}
