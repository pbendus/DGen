package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.ClassificationSystem;
import org.springframework.stereotype.Service;

@Service
public class ClassificationSystemService extends BaseServiceImpl<ClassificationSystem> {
  public ClassificationSystemService(
      Dao<ClassificationSystem, Integer> dao) {
    super(dao);
  }
}
