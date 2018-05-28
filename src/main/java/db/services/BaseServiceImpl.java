package db.services;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseServiceImpl<T> implements Service<T> {

  static final Logger LOGGER = LogManager.getLogger();
  private Dao<T, Integer> dao;

  public BaseServiceImpl(Dao<T, Integer> dao) {
    this.dao = dao;
  }

  @Override public T getById(int id) throws SQLException {
    final T t = dao.queryForId(id);
    if (t == null) {
      LOGGER.warn(String.format("Object with id{%d} was not found", id));
    }
    LOGGER.info(String.format("Object with id{%d} has been returned", id));
    return t;
  }

  @Override public List<T> getAll() throws SQLException {
    final List<T> ts = dao.queryForAll();
    LOGGER.info(String.format("The storage contains %d object(s)", ts.size()));
    return ts;
  }

  @Override public int create(T t) throws SQLException {
    final int result = dao.create(t);

    if (result == 1) {
      LOGGER.info(
          String.format("The object %s has been successfully inserted into the storage", t));
    }

    return result;
  }

  @Override public int update(T t) throws SQLException {
    final int result = dao.update(t);

    if (result == 1) {
      LOGGER.info(
          String.format("The object %s has been successfully updated", t));
    }

    return result;
  }

  @Override public int delete(int id) throws SQLException {
    final int result = dao.deleteById(id);

    if (result == 1) {
      LOGGER.info(
          String.format("The object with %d has been successfully deleted from the storage", id));
    }

    return result;
  }

  public Dao<T, Integer> getDao() {
    return dao;
  }
}
