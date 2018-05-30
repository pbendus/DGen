package db;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import db.entities.AccessRequirements;
import db.entities.ClassificationSystem;
import db.entities.Diploma;
import db.entities.DiplomaSubject;
import db.entities.DurationOfTraining;
import db.entities.EducationalComponent;
import db.entities.EducationalComponentType;
import db.entities.FieldOfStudy;
import db.entities.MainField;
import db.entities.ModeOfStudy;
import db.entities.NationalGrade;
import db.entities.OfficialDurationOfProgramme;
import db.entities.PreviousDocument;
import db.entities.Protocol;
import db.entities.RatingPoint;
import db.entities.Student;
import java.sql.SQLException;
import ui.models.ProfessionalStatus;

public class DBUtils {
  /**
   * Create all tables if they do not already exist
   */
  public static void createAllTablesIfNotExists(ConnectionSource connectionSource)
      throws SQLException {
    TableUtils.createTableIfNotExists(connectionSource, FieldOfStudy.class);
    TableUtils.createTableIfNotExists(connectionSource, MainField.class);
    TableUtils.createTableIfNotExists(connectionSource, OfficialDurationOfProgramme.class);
    TableUtils.createTableIfNotExists(connectionSource, AccessRequirements.class);
    TableUtils.createTableIfNotExists(connectionSource, DiplomaSubject.class);
    TableUtils.createTableIfNotExists(connectionSource, PreviousDocument.class);
    TableUtils.createTableIfNotExists(connectionSource, ModeOfStudy.class);
    TableUtils.createTableIfNotExists(connectionSource, ClassificationSystem.class);
    TableUtils.createTableIfNotExists(connectionSource, Protocol.class);
    TableUtils.createTableIfNotExists(connectionSource, DurationOfTraining.class);
    TableUtils.createTableIfNotExists(connectionSource, Student.class);
    TableUtils.createTableIfNotExists(connectionSource, Diploma.class);
    TableUtils.createTableIfNotExists(connectionSource, RatingPoint.class);
    TableUtils.createTableIfNotExists(connectionSource, NationalGrade.class);
    TableUtils.createTableIfNotExists(connectionSource, EducationalComponentType.class);
    TableUtils.createTableIfNotExists(connectionSource, EducationalComponent.class);
  }

  /**
   * Clear all tables
   */
  public static void clearAllTables(ConnectionSource connectionSource)
      throws SQLException {
    TableUtils.clearTable(connectionSource, FieldOfStudy.class);
    TableUtils.clearTable(connectionSource, MainField.class);
    TableUtils.clearTable(connectionSource, OfficialDurationOfProgramme.class);
    TableUtils.clearTable(connectionSource, AccessRequirements.class);
    TableUtils.clearTable(connectionSource, DiplomaSubject.class);
    TableUtils.clearTable(connectionSource, PreviousDocument.class);
    TableUtils.clearTable(connectionSource, ModeOfStudy.class);
    TableUtils.clearTable(connectionSource, ClassificationSystem.class);
    TableUtils.clearTable(connectionSource, ProfessionalStatus.class);
    TableUtils.clearTable(connectionSource, Protocol.class);
    TableUtils.clearTable(connectionSource, DurationOfTraining.class);
    TableUtils.clearTable(connectionSource, Student.class);
    TableUtils.clearTable(connectionSource, Diploma.class);
    TableUtils.clearTable(connectionSource, RatingPoint.class);
    TableUtils.clearTable(connectionSource, NationalGrade.class);
    TableUtils.clearTable(connectionSource, EducationalComponentType.class);
    TableUtils.clearTable(connectionSource, EducationalComponent.class);
  }
}
