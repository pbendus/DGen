package db.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

public class BaseServiceImpl<T> implements Service<T> {

  private Dao<T, Integer> dao;

  public BaseServiceImpl(Dao<T, Integer> dao) {
    this.dao = dao;
  }

  @Override public T getById(int id) throws SQLException {
    return dao.queryForId(id);
  }

  @Override public List<T> getAll() throws SQLException {
    return dao.queryForAll();
  }

  @Override public int create(T t) throws SQLException {
    return dao.create(t);
  }

  @Override public int update(T t) throws SQLException {
    return dao.update(t);
  }

  @Override public int delete(int id) throws SQLException {
    return dao.deleteById(id);
  }

  public Dao<T, Integer> getDao() {
    return dao;
  }
}
