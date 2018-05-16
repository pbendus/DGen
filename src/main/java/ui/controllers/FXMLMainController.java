package ui.controllers;

import db.mapper.StudentMapper;
import db.services.StudentService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.models.Student;

@Controller("fxmlMainController")
public class FXMLMainController implements Initializable {

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

  private ObservableList<Student> students = FXCollections.observableArrayList();

  private StudentMapper studentMapper;
  private StudentService studentService;

  @Autowired
  public FXMLMainController(StudentMapper studentMapper, StudentService studentService) {
    this.studentMapper = studentMapper;
    this.studentService = studentService;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    try {
      students.addAll(studentMapper.map(studentService.getAll()));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      students.add(null);
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
