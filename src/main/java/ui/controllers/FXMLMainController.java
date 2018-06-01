package ui.controllers;

import db.mapper.StudentMapper;
import db.services.StudentService;

import doc_utils.DocWorker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.Student;
import ui.utils.SpringFXMLLoader;

@Controller("fxmlMainController")
public class FXMLMainController implements Initializable {

  private static final Logger LOGGER = LogManager.getLogger();

  @FXML
  public Menu menuSettings;

  @FXML
  public MenuItem menuItemProtocols;
  @FXML
  public MenuItem menuItemFieldOfStudy;
  @FXML
  public MenuItem menuItemMainField;
  @FXML
  public MenuItem menuItemGroups;
  @FXML
  public MenuItem menuItemOfficialDurationOfProgramme;
  @FXML
  public MenuItem menuItemDurationOfTraining;

  @FXML
  private TableView<Student> tblView;

  @FXML
  private TableColumn<Student, CheckBox> tblColCheckbox;

  @FXML
  private TableColumn<Student, Integer> tblColId;

  @FXML
  private TableColumn<Student, String> tblColStudent;

  @FXML
  private CheckBox chkboxSelectAll;

  @FXML
  private Button btnAddStudent;

  @FXML
  public Button btnGenerate;

  private ObservableList<Student> students = FXCollections.observableArrayList();

  private StudentMapper studentMapper;
  private StudentService studentService;

  private FXMLStudentController fxmlStudentController;

  private DocWorker docWorker;
  private FXMLSettingsController fxmlSettingsController;

  @Autowired
  public FXMLMainController(StudentMapper studentMapper, StudentService studentService,
      FXMLStudentController fxmlStudentController, DocWorker docWorker,
      FXMLSettingsController fxmlSettingsController) {
    this.studentMapper = studentMapper;
    this.studentService = studentService;
    this.fxmlStudentController = fxmlStudentController;
    this.docWorker = docWorker;
    this.fxmlSettingsController = fxmlSettingsController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    try {
      List<db.entities.Student> list = studentService.getAll();
      students.addAll(studentMapper.map(list));
    } catch (SQLException e) {
      e.printStackTrace();
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

    btnAddStudent.setOnAction(e -> openStudentModalWindow());
    btnGenerate.setOnAction(event -> generateDocuments());
    menuItemProtocols.setOnAction(
        event -> openSettingsModalWindow(FXMLSettingsController.Tab.PROTOCOLS));
    menuItemGroups.setOnAction(
        event -> openSettingsModalWindow(FXMLSettingsController.Tab.GROUPS));
    menuItemDurationOfTraining.setOnAction(
        event -> openSettingsModalWindow(FXMLSettingsController.Tab.DURATION_OF_TRAINING));
    menuItemFieldOfStudy.setOnAction(
        event -> openSettingsModalWindow(FXMLSettingsController.Tab.FIELD_OF_STUDY));
    menuItemMainField.setOnAction(
        event -> openSettingsModalWindow(FXMLSettingsController.Tab.MAIN_FIELD));
    menuItemOfficialDurationOfProgramme.setOnAction(
        event -> openSettingsModalWindow(
            FXMLSettingsController.Tab.OFFICIAL_DURATION_OF_PROGRAMME));
  }

  private void generateDocuments() {
    if (containsSelectedStudents()) {
      for (Student student :
          students) {
        if (student.getSelect().isSelected()) {
          try {
            docWorker.generateDocument(student.getId(), student.getFamilyNameTr());
          } catch (IOException | XmlException | SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
          }
        }
      }
    }
  }

  private boolean containsSelectedStudents() {
    return students.stream().anyMatch(student -> student.getSelect().isSelected());
  }

  private void openStudentModalWindow() {
    try {
      fxmlStudentController.display();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void openSettingsModalWindow(FXMLSettingsController.Tab tab) {
    try {
      fxmlSettingsController.setTab(tab);
      fxmlSettingsController.display();
    } catch (Exception e) {
      e.printStackTrace();
    }
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

  public void display(Stage primaryStage) throws Exception {
    Parent root = SpringFXMLLoader.create()
        .applicationContext(Main.getContext())
        .location(FXMLMainController.class
            .getResource("../../fxml/main.fxml"))
        .load();

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);

    primaryStage.setTitle("Генерація додатків до дипломів");

    //setting up min width & height parameters for window
    primaryStage.setMinWidth(600);
    primaryStage.setMinHeight(400);

    primaryStage.setMaximized(true);
    primaryStage.show();
  }
}
