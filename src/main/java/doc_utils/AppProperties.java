package doc_utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

@Component
public class AppProperties {

    private final static Logger LOGGER = LogManager.getLogger();

    public void changeDB(String newDB) throws IOException {
        final Properties propertiesNew = new Properties();
        final Properties propertiesOld = new Properties();
        OutputStream output;
        InputStream input;

        input = new FileInputStream("src/main/resources/database.properties");

        propertiesOld.load(input);

        final String connectionValue = propertiesOld.getProperty("db.checkConnectionsEveryMillis");
        input.close();

        output = new FileOutputStream("src/main/resources/database.properties");
        propertiesNew.setProperty("db.databaseUrl", "jdbc:sqlite:" + newDB);
        propertiesNew.setProperty("db.checkConnectionsEveryMillis", connectionValue);
        propertiesNew.store(output, null);

        LOGGER.info("db.databaseUrl has been changed");
    }

    public String getDataBaseUrl() throws IOException {
        InputStream input;

        final Properties properties = new Properties();

        input = new FileInputStream("src/main/resources/database.properties");

        properties.load(input);

        return properties.getProperty("db.databaseUrl");
    }

    public void changeInputFile(String newInputFile) throws IOException {
        final Properties propertiesNew = new Properties();
        final Properties propertiesOld = new Properties();
        OutputStream output;
        InputStream input;

        input = new FileInputStream("src/main/resources/doc.properties");
        propertiesOld.load(input);

        final String pattern = propertiesOld.getProperty("doc.pattern");
        input.close();
        output = new FileOutputStream("src/main/resources/doc.properties");
        propertiesNew.setProperty("doc.pattern", pattern);
        propertiesNew.setProperty("doc.inputFilePath", newInputFile);
        propertiesNew.store(output, null);

        LOGGER.info("doc.inputFilePath has been changed");
    }

    public String getInputFilePath() throws IOException {
        InputStream input;

        final Properties properties = new Properties();

        input = new FileInputStream("src/main/resources/doc.properties");

        properties.load(input);

        return properties.getProperty("doc.inputFilePath");
    }

    public void changePattern(String patternNew) throws IOException {
        final Properties propertiesNew = new Properties();
        final Properties propertiesOld = new Properties();
        OutputStream output;
        InputStream input;

        input = new FileInputStream("src/main/resources/doc.properties");

        propertiesOld.load(input);
        final String inputFilePath = propertiesOld.getProperty("doc.inputFilePath");
        input.close();

        output = new FileOutputStream("src/main/resources/doc.properties");
        propertiesNew.setProperty("doc.pattern", patternNew);
        propertiesNew.setProperty("doc.inputFilePath", inputFilePath);
        propertiesNew.store(output, null);

        LOGGER.info("doc.pattern has been changed");
    }

    public String getStudentRatingInputPath() throws IOException {
        InputStream input;

        final Properties properties = new Properties();

        input = new FileInputStream("src/main/resources/doc.properties");

        properties.load(input);

        return properties.getProperty("doc.studentRatingFilePath");
    }
}
