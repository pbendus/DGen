package db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import db.DBUtils;
import db.entities.AccessRequirements;
import db.entities.Diploma;
import db.services.AccessRequirementsService;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  private static final String DB_NAME = "bachelor_diploma.db";

  public static void main(String[] args) {
    try {
      JdbcConnectionSource connectionSource =
          new JdbcPooledConnectionSource("jdbc:sqlite:" + DB_NAME);
      DBUtils.createAllTablesIfNotExists(connectionSource);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
