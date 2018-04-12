package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.FieldOfStudy;
import db.entities.MainField;

public class MainFieldService extends BaseServiceImpl<MainField> {
  public MainFieldService(
      Dao<MainField, Integer> dao) {
    super(dao);
  }
}
