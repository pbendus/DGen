package ui;

import db.configuration.DataSourceConfig;
import doc_utils.DocConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.controllers.FXMLMainController;
import ui.utils.AppConfig;

public class Main extends Application {

    private static AnnotationConfigApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        super.init();
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DataSourceConfig.class,
            DocConfig.class);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLMainController fxmlMainController = (FXMLMainController) getContext()
                    .getBean("fxmlMainController");
            fxmlMainController.display(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static AnnotationConfigApplicationContext getContext() {
        return Main.applicationContext;
    }
}
