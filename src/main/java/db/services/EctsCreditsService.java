package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EctsCredits;
import org.springframework.stereotype.Service;

@Service
public class EctsCreditsService
    extends BaseServiceImpl<EctsCredits> {
  public EctsCreditsService(
      Dao<EctsCredits, Integer> dao) {
    super(dao);
  }
}
