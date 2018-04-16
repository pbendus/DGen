package doc_utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("doc_utils")
@PropertySource("classpath:doc.properties")
public class DocConfig {

  @Value("${doc.inputFilePath}")
  private String inputFilePath;

  @Bean
  public XWPFDocument getInputDocument() throws IOException {
    FileInputStream fis = new FileInputStream(new File(inputFilePath));

    return new XWPFDocument(fis);
  }
}
