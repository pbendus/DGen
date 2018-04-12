package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EducationalComponentType;
import db.entities.FieldOfStudy;
import java.lang.reflect.Field;

public class FieldOfStudyService extends BaseServiceImpl<FieldOfStudy> {
  public FieldOfStudyService(
      Dao<FieldOfStudy, Integer> dao) {
    super(dao);
  }
}
