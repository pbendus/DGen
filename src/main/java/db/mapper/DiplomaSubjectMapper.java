package db.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiplomaSubjectMapper
    extends Mapper<db.entities.DiplomaSubject, ui.models.DiplomaSubject> {

  @Autowired
  public DiplomaSubjectMapper() {
  }

  @Override public ui.models.DiplomaSubject map(db.entities.DiplomaSubject value) {
    final ui.models.DiplomaSubject diplomaSubject = new ui.models.DiplomaSubject();
    diplomaSubject.setId(value.getId());
    diplomaSubject.setSubjectEN(value.getSubjectEN());
    diplomaSubject.setSubjectUK(value.getSubjectUK());
    return diplomaSubject;
  }

  @Override
  public db.entities.DiplomaSubject reverseMap(ui.models.DiplomaSubject value) {
    final db.entities.DiplomaSubject diplomaSubject = new db.entities.DiplomaSubject();
    diplomaSubject.setId(value.getId());
    diplomaSubject.setSubjectEN(value.getSubjectEN());
    diplomaSubject.setSubjectUK(value.getSubjectUK());
    return diplomaSubject;
  }
}
