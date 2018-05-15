package db.configuration;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import db.entities.AccessRequirements;
import db.entities.ClassificationSystem;
import db.entities.Diploma;
import db.entities.DiplomaSubject;
import db.entities.DurationOfTraining;
import db.entities.EducationalComponent;
import db.entities.EducationalComponentTemplate;
import db.entities.EducationalComponentType;
import db.entities.FieldOfStudy;
import db.entities.MainField;
import db.entities.ModeOfStudy;
import db.entities.NationalGrade;
import db.entities.OfficialDurationOfProgramme;
import db.entities.PreviousDocument;
import db.entities.ProfessionalStatus;
import db.entities.Protocol;
import db.entities.RatingPoint;
import db.entities.Student;
import java.sql.SQLException;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("db")
@PropertySource(value = {"classpath:database.properties"})
public class DataSourceConfig {

  @Resource
  private Environment environment;

  @Bean(destroyMethod = "close")
  public ConnectionSource getConnectionSource() throws SQLException {
    ConnectionSource connectionSource =
        new JdbcPooledConnectionSource(environment.getRequiredProperty("db.databaseUrl"));
    ((JdbcPooledConnectionSource) connectionSource).setCheckConnectionsEveryMillis(
        Long.parseLong(environment.getRequiredProperty("db.checkConnectionsEveryMillis")));
    return connectionSource;
  }

  @Bean
  public Dao<AccessRequirements, Integer> getAccessRequirementsDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, AccessRequirements.class);
  }

  @Bean
  public Dao<ClassificationSystem, Integer> getClassificationSystemDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, ClassificationSystem.class);
  }

  @Bean
  public Dao<DiplomaSubject, Integer> getDiplomaSubjectDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, DiplomaSubject.class);
  }

  @Bean
  public Dao<DurationOfTraining, Integer> getDurationOfTrainingDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, DurationOfTraining.class);
  }

  @Bean
  public Dao<EducationalComponentTemplate, Integer> getEducationalComponentTemplateDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, EducationalComponentTemplate.class);
  }

  @Bean
  public Dao<EducationalComponent, Integer> getEducationalComponentDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, EducationalComponent.class);
  }

  @Bean
  public Dao<EducationalComponentType, Integer> getEducationalComponentTypeDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, EducationalComponentType.class);
  }

  @Bean
  public Dao<FieldOfStudy, Integer> getFieldOfStudyDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, FieldOfStudy.class);
  }

  @Bean
  public Dao<MainField, Integer> getMainFieldDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, MainField.class);
  }

  @Bean
  public Dao<ModeOfStudy, Integer> getModeOfStudyDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, ModeOfStudy.class);
  }

  @Bean
  public Dao<NationalGrade, Integer> getNationalGradeDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, NationalGrade.class);
  }

  @Bean
  public Dao<OfficialDurationOfProgramme, Integer> getOfficialDurationOfProgrammeDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, OfficialDurationOfProgramme.class);
  }

  @Bean
  public Dao<PreviousDocument, Integer> getPreviousDocumentDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, PreviousDocument.class);
  }

  @Bean
  public Dao<ProfessionalStatus, Integer> getProfessionalStatusDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, ProfessionalStatus.class);
  }

  @Bean
  public Dao<Protocol, Integer> getProtocolDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, Protocol.class);
  }

  @Bean
  public Dao<RatingPoint, Integer> getRatingPointDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, RatingPoint.class);
  }

  @Bean
  public Dao<Student, Integer> getStudentDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, Student.class);
  }

  @Bean
  public Dao<Diploma, Integer> getDiplomaDao(
      ConnectionSource connectionSource)
      throws SQLException {
    return DaoManager.createDao(connectionSource, Diploma.class);
  }
}
