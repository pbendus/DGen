package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.ClassificationSystem;
import db.entities.DiplomaSubject;

public class DiplomaSubjectService extends BaseServiceImpl<DiplomaSubject> {
  public DiplomaSubjectService(
      Dao<DiplomaSubject, Integer> dao) {
    super(dao);
  }
}
