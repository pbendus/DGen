package ui;

import db.configuration.DataSourceConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.controllers.FXMLMainController;
import ui.utils.AppConfig;

public class Main extends Application {

    private static AnnotationConfigApplicationContext applicationContext;

    private FXMLMainController fxmlMainController;

    @Override
    public void init() throws Exception {
        super.init();
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DataSourceConfig.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        fxmlMainController = (FXMLMainController) applicationContext.getBean("fxmlMainController");

        //setting up min width & height parameters for window
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);

        primaryStage.setScene(fxmlMainController.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static AnnotationConfigApplicationContext getContext() {
        return Main.applicationContext;
    }
}
