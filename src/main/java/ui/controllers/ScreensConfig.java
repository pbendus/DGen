/*
package ui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Observable;
import java.util.Observer;

@Configuration
@Lazy
public class ScreensConfig{

    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 400;
    private static final String TITLE = "Document";

    private FXMLMainController fxmlMainController;

    private Stage stage;
    private Scene scene;

    @Autowired
    public ScreensConfig(FXMLMainController fxmlMainController) {
        this.fxmlMainController = fxmlMainController;
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void showMainScreen() {

        stage.setTitle(TITLE);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("../../fxml/main.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("HELP");
        }

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
*/
