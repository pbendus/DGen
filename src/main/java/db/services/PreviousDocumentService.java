package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.PreviousDocument;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;

@Service
public class PreviousDocumentService
    extends BaseServiceImpl<PreviousDocument> {
  public PreviousDocumentService(
      Dao<PreviousDocument, Integer> dao) {
    super(dao);
  }

  public PreviousDocument getByName(String name) throws SQLException {
    return getDao().queryForEq("name", name).get(0);
  }
}
