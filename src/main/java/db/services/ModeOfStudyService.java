package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.MainField;
import db.entities.ModeOfStudy;

public class ModeOfStudyService extends BaseServiceImpl<ModeOfStudy> {
  public ModeOfStudyService(
      Dao<ModeOfStudy, Integer> dao) {
    super(dao);
  }
}
