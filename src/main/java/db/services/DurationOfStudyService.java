package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.DurationOfStudy;
import org.springframework.stereotype.Service;

@Service
public class DurationOfStudyService extends BaseServiceImpl<DurationOfStudy> {
  public DurationOfStudyService(
      Dao<DurationOfStudy, Integer> dao) {
    super(dao);
  }
}
