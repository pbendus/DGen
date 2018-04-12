package db.services;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import db.entities.AccessRequirements;

public class AccessRequirementsService extends BaseServiceImpl<AccessRequirements> {
  public AccessRequirementsService(
      Dao<AccessRequirements, Integer> dao) {
    super(dao);
  }
}
