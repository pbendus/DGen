package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.MainField;
import org.springframework.stereotype.Service;

@Service
public class MainFieldService extends BaseServiceImpl<MainField> {
  public MainFieldService(
      Dao<MainField, Integer> dao) {
    super(dao);
  }
}
