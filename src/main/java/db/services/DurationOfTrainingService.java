package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.DiplomaSubject;
import db.entities.DurationOfTraining;

public class DurationOfTrainingService extends BaseServiceImpl<DurationOfTraining> {
  public DurationOfTrainingService(
      Dao<DurationOfTraining, Integer> dao) {
    super(dao);
  }
}
