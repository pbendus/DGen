package ui.controllers;

import db.mapper.VariableMapper;
import db.services.VariableService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.Variable;
import ui.utils.AlertBox;
import ui.utils.SpringFXMLLoader;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Controller("fxmlVariablesController")
public class FXMLVariablesController implements Initializable {

    @FXML
    private TableView<Variable> tvVariables;
    @FXML
    private TableColumn<Variable, Integer> tcNumber;
    @FXML
    private TableColumn<Variable, String> tcName;
    @FXML
    private TableColumn<Variable, String> tcValue;

    private Stage stage;

    private Logger LOGGER = LogManager.getLogger();

    private ObservableList<Variable> variableObservableList = FXCollections.observableArrayList();

    private VariableService variableService;
    private VariableMapper variableMapper;

    @Autowired
    public FXMLVariablesController(VariableService variableService, VariableMapper variableMapper) {
        this.variableService = variableService;
        this.variableMapper = variableMapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeVariablesList();
        for (Variable variable :
                variableObservableList) {
            System.out.println(variable.getVariable());
        }
        initializeTableView();
    }

    private void initializeVariablesList() {
        try {
            variableObservableList.addAll(variableMapper.map(variableService.getAll()));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося отримати список змінних", e);
        }
    }

    private void initializeTableView() {
        tcNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("variable"));
        tcValue.setCellValueFactory(new PropertyValueFactory<>("description"));

        tvVariables.setItems(variableObservableList);
    }

    void display() throws Exception {
        Parent root = SpringFXMLLoader.create()
                .applicationContext(Main.getContext())
                .location(FXMLStudentController.class
                        .getResource("../../fxml/variables.fxml"))
                .load();

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Список змінних");

        //setting up min width & height parameters for window
        stage.setMinWidth(600);
        stage.setMinHeight(400);

        stage.showAndWait();
    }
}
