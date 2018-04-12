package db.services;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {

  T getById(int id) throws SQLException;

  List<T> getAll() throws SQLException;

  int create(T t) throws SQLException;

  int update(T t) throws SQLException;

  int delete(int id) throws SQLException;
}
