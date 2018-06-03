package doc_utils;

import db.configuration.DataSourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(DocConfig.class, DataSourceConfig.class);

        //DocWorker docWorker = (DocWorker) context.getBean("docWorker");
        //try {
        //  docWorker.generateDocument(2, "output.docx");
        //} catch (IOException | SQLException | XmlException e) {
        //  e.printStackTrace();
        //}

        AppProperties appProperties = (AppProperties) context.getBean("appProperties");
        appProperties.changeDB("bachelor_diploma.db");
    }
}
