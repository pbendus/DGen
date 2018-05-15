package ui.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ui.models.DiplomaSubject;
import ui.models.PreviousDocument;
import ui.models.Protocol;
import ui.models.Student;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class FXMLMainController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView tblView;

    @FXML
    private TableColumn<Student, CheckBox> tblColCheckbox;

    @FXML
    private TableColumn<Student, Integer> tblColId;

    @FXML
    private TableColumn<Student, String> tblColStudent;

    @FXML
    private CheckBox chkboxSelectAll;

    ObservableList<Student> students = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 100; i++) {
            students.add(new Student(i + 1, "Бендус", "Павло Мирославович", "Bendus", "Pavlo Myroslavovyck",
                    new Date(1997, 03, 26), new Protocol(), new DiplomaSubject(),
                    new PreviousDocument()));
        }

        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColCheckbox.setCellValueFactory(new PropertyValueFactory<>("select"));
        tblColStudent.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        tblView.setItems(students);

        chkboxSelectAll.setOnAction(e -> {
            if (!chkboxSelectAll.isSelected()) {
                setChboxUnselectAll();
            } else {
                setChkboxSelectAll();
            }
        });

        tblView.setRowFactory(param -> {
            final TableRow<Student> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-pref-width: 150px;");

            MenuItem editItem = new MenuItem("Edit");
            editItem.setOnAction(e -> System.out.println("Edit Item"));

            MenuItem removeItem = new MenuItem("Delete");
            removeItem.setOnAction(e -> tblView.getItems().remove(row.getItem()));
            contextMenu.getItems().addAll(editItem, removeItem);

            // only display context menu for non-null items:
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(contextMenu)
                            .otherwise((ContextMenu) null));
            return row;
        });
    }

    private void setChkboxSelectAll() {
        for (Student student :
                students) {
            student.getSelect().setSelected(true);
        }
    }

    private void setChboxUnselectAll() {
        for (Student student :
                students) {
            student.getSelect().setSelected(false);
        }
    }

    public Scene getScene() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../../fxml/main.fxml"));

        return new Scene(root);
    }
}
