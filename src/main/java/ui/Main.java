package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.controllers.FXMLMainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Diplomas Additions Generator");

        FXMLMainController mainController = new FXMLMainController();

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);

        primaryStage.setScene(mainController.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
