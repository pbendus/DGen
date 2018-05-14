package db.services;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestingDataSourceConfig {

  @Bean(destroyMethod = "close")
  public ConnectionSource getConnectionSource() throws SQLException {
    return new JdbcPooledConnectionSource("jdbc:sqlite:bachelor_diploma_test.db");
  }
}
