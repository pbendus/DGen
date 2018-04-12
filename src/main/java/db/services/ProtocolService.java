package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.ProfessionalStatus;
import db.entities.Protocol;

public class ProtocolService
    extends BaseServiceImpl<Protocol> {
  public ProtocolService(
      Dao<Protocol, Integer> dao) {
    super(dao);
  }
}
