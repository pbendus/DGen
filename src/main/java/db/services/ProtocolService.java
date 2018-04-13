package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Protocol;
import org.springframework.stereotype.Service;

@Service
public class ProtocolService
    extends BaseServiceImpl<Protocol> {
  public ProtocolService(
      Dao<Protocol, Integer> dao) {
    super(dao);
  }
}
