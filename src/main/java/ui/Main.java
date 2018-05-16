package ui;

import db.configuration.DataSourceConfig;
import db.mapper.StudentMapper;
import db.services.StudentService;
import doc_utils.DocConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ui.controllers.FXMLMainController;
import ui.models.Student;
import ui.utils.UIConfig;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Diplomas Additions Generator");

        //setting up min width & height parameters for window
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        StudentService studentMapper = (StudentService) context.getBean("studentMapper");
        System.out.println(studentMapper.toString());

        //FXMLMainController fxmlMainController = (FXMLMainController) context.getBean("fxmlMainController");

        //primaryStage.setScene(fxmlMainController.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
