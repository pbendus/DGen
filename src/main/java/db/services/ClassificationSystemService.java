package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.AccessRequirements;
import db.entities.ClassificationSystem;

public class ClassificationSystemService extends BaseServiceImpl<ClassificationSystem> {
  public ClassificationSystemService(
      Dao<ClassificationSystem, Integer> dao) {
    super(dao);
  }
}
