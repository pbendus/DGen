package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Diploma;
import org.springframework.stereotype.Service;

@Service
public class DiplomaService extends BaseServiceImpl<Diploma> {
  public DiplomaService(
      Dao<Diploma, Integer> dao) {
    super(dao);
  }
}
