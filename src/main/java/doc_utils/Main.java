package doc_utils;

import db.configuration.DataSourceConfig;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.xmlbeans.XmlException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
  public static void main(String[] args) {

    AbstractApplicationContext context =
        new AnnotationConfigApplicationContext(DocConfig.class, DataSourceConfig.class);

    DocWorker docWorker = (DocWorker) context.getBean("docWorker");
    try {
      docWorker.generateDocument(2, "output.docx");
    } catch (IOException | SQLException | XmlException e) {
      e.printStackTrace();
    }
  }
}
