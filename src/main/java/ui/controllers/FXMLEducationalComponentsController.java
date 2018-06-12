package ui.controllers;

import db.mappers.EducationalComponentMapper;
import db.mappers.EducationalComponentTemplateMapMapper;
import db.services.EducationalComponentService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.dialog.Dialogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.EducationalComponentWithData;
import ui.models.TemplateWithEducationalComponents;
import ui.utils.AlertBox;
import ui.utils.SpringFXMLLoader;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@Controller("fxmlEducationalComponentsController")
public class FXMLEducationalComponentsController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger();

    @FXML
    public TabPane tabPane;
    private Stage stage;

    private EducationalComponentService educationalComponentService;
    private EducationalComponentTemplateMapMapper educationalComponentTemplateMapMapper;

    private List<TemplateWithEducationalComponents> templateWithEducationalComponents;
    private EducationalComponentMapper educationalComponentMapper;

    @Autowired
    public FXMLEducationalComponentsController(EducationalComponentService educationalComponentService,
                                               EducationalComponentTemplateMapMapper educationalComponentTemplateMapMapper, EducationalComponentMapper educationalComponentMapper) {
        this.educationalComponentService = educationalComponentService;
        this.educationalComponentTemplateMapMapper = educationalComponentTemplateMapMapper;
        this.educationalComponentMapper = educationalComponentMapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Task<Void> service = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    templateWithEducationalComponents = educationalComponentTemplateMapMapper
                            .mapAll(educationalComponentService.getComponentTemplateWithDiplomas());
                    Platform.runLater(() -> {
                        for (TemplateWithEducationalComponents template :
                                templateWithEducationalComponents) {
                            TableView<EducationalComponentWithData> tableView =
                                    new TableView<>();
                            AnchorPane.setTopAnchor(tableView, 10.0);
                            AnchorPane.setBottomAnchor(tableView, 10.0);
                            AnchorPane.setLeftAnchor(tableView, 10.0);
                            AnchorPane.setRightAnchor(tableView, 10.0);
                            tableView.setEditable(true);
                            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                            TableColumn<EducationalComponentWithData, Integer> tcId = new TableColumn<>(" №");
                            TableColumn<EducationalComponentWithData, String> tcStudent = new TableColumn<>("Студент");
                            TableColumn<EducationalComponentWithData, String> tcGroup = new TableColumn<>("Група");
                            TableColumn<EducationalComponentWithData, Integer> tcResult = new TableColumn<>("Бали");
                            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
                            tcStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
                            tcGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
                            tcResult.setCellValueFactory(new PropertyValueFactory<>("nationalScore"));
                            tableView.getColumns().add(tcId);
                            tableView.getColumns().add(tcStudent);
                            tableView.getColumns().add(tcGroup);
                            tableView.getColumns().add(tcResult);
                            final AnchorPane content = new AnchorPane(tableView);
                            tableView.setItems(template.getEducationalComponents());
                            tcResult.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                            tcResult.setOnEditCommit(event1 -> {
                                event1.getRowValue().getEducationalComponent().setNationalScore(event1.getNewValue());
                                event1.getRowValue().setNationalScore(event1.getNewValue());
                                try {
                                    educationalComponentService.update(
                                            educationalComponentMapper.reverseMap(event1.getRowValue().getEducationalComponent()));
                                } catch (SQLException e) {
                                    LOGGER.error(e.getMessage());
                                    AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                                            "Не вдалося обновити дані", e);
                                }
                            });
                            tabPane.getTabs().add(new Tab(template.getEducationalComponentTemplate().getCourseTitleSplit(), content));
                        }
                    });
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                    AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                            "Не вдалося завантажити дані", e);
                }
                return null;
            }
        };

        Dialogs.create()
                .owner(stage)
                .title("Progress Dialog")
                .masthead("Завантаження результатів")
                .showWorkerProgress(service);
        Thread thread = new Thread(service);
        thread.start();

        service.setOnSucceeded(event -> {

        });


    }

    private void closeWindow() {
        stage.close();
    }

    void display() throws Exception {
        Parent root = SpringFXMLLoader.create()
                .applicationContext(Main.getContext())
                .location(FXMLEducationalComponentsController.class
                        .getResource("../../fxml/educationalComponents.fxml"))
                .load();

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Результати навчання");

        //setting up min width & height parameters for window
        stage.setMinWidth(900);
        stage.setMinHeight(600);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
