package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.Group;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends BaseServiceImpl<Group> {
  public GroupService(
      Dao<Group, Integer> dao) {
    super(dao);
  }

  public Group getByName(String name) throws SQLException {
    return getDao().queryForEq("name", name).get(0);
  }
}
