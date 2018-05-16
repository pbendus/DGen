package ui;

import db.configuration.DataSourceConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ui.controllers.FXMLMainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Diplomas Additions Generator");

        //setting up min width & height parameters for window
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        FXMLMainController fxmlMainController = (FXMLMainController) context.getBean("fxmlMainController");

        primaryStage.setScene(fxmlMainController.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
