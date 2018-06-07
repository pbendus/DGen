package ui;

import db.configuration.DataSourceConfig;
import doc_utils.DocConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.controllers.FXMLMainController;
import ui.utils.AlertBox;
import ui.utils.AppConfig;

public class Main extends Application {

    private static final Logger LOGGER = LogManager.getLogger();
    private static AnnotationConfigApplicationContext applicationContext;

    @Override
    public void init() {
        try {
            super.init();

            applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DataSourceConfig.class,
                    DocConfig.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося завантажити необхідні компоненти", e);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLMainController fxmlMainController = (FXMLMainController) getContext()
                    .getBean("fxmlMainController");
            fxmlMainController.display(primaryStage);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося запустити головне вікно", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static AnnotationConfigApplicationContext getContext() {
        return Main.applicationContext;
    }
}
