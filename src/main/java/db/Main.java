package db;

import db.configuration.DataSourceConfig;
import db.services.AccessRequirementsService;
import db.services.ClassificationSystemService;
import java.sql.SQLException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {

  public static void main(String[] args) {
    AbstractApplicationContext context =
        new AnnotationConfigApplicationContext(DataSourceConfig.class);

    AccessRequirementsService accessRequirementsService =
        (AccessRequirementsService) context.getBean("accessRequirementsService");

    ClassificationSystemService classificationSystemService =
        (ClassificationSystemService) context.getBean("classificationSystemService");

    try {
      System.out.println(accessRequirementsService.getAll());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
