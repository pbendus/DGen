package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.DurationOfTraining;
import org.springframework.stereotype.Service;

@Service
public class DurationOfTrainingService extends BaseServiceImpl<DurationOfTraining> {
  public DurationOfTrainingService(
      Dao<DurationOfTraining, Integer> dao) {
    super(dao);
  }
}
