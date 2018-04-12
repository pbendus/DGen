package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EducationalComponent;
import db.entities.EducationalComponentType;

public class EducationalComponentTypeService extends BaseServiceImpl<EducationalComponentType> {
  public EducationalComponentTypeService(
      Dao<EducationalComponentType, Integer> dao) {
    super(dao);
  }
}
