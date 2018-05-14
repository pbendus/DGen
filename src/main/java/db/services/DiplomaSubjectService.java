package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.DiplomaSubject;
import org.springframework.stereotype.Service;

@Service
public class DiplomaSubjectService extends BaseServiceImpl<DiplomaSubject> {
  public DiplomaSubjectService(
      Dao<DiplomaSubject, Integer> dao) {
    super(dao);
  }
}
