package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.DiplomaSubject;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;

@Service
public class DiplomaSubjectService extends BaseServiceImpl<DiplomaSubject> {
  public DiplomaSubjectService(
      Dao<DiplomaSubject, Integer> dao) {
    super(dao);
  }

  public DiplomaSubject getByName(String subjectUK, String subjectEN) throws SQLException {
    HashMap<String, Object> values = new HashMap<>();
    values.put("subject_uk", subjectUK);
    values.put("subject_en", subjectEN);
    return getDao().queryForFieldValues(values).get(0);
  }
}
