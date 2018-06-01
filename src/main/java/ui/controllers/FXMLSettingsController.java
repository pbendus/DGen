package ui.controllers;

import db.mapper.FieldOfStudyMapper;
import db.mapper.MainFieldMapper;
import db.mapper.ProtocolMapper;
import db.services.FieldOfStudyService;
import db.services.MainFieldService;
import db.services.ProtocolService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.FieldOfStudy;
import ui.models.MainField;
import ui.models.Protocol;
import ui.utils.SpringFXMLLoader;
import ui.utils.Validation;

@Controller("fxmlSettingsController")
public class FXMLSettingsController implements Initializable {

  private static final Logger LOGGER = LogManager.getLogger();

  @FXML
  public TextField tfNameUKProtocol;
  @FXML
  public TextField tfNameENProtocol;
  @FXML
  public Button btnAddProtocol;
  @FXML
  public TableView<Protocol> tblProtocols;
  @FXML
  public TableColumn<Protocol, Integer> tblColIdProtocol;
  @FXML
  public TableColumn<Protocol, String> tblColNameUKProtocol;
  @FXML
  public TableColumn<Protocol, String> tblColNameENProtocol;

  @FXML
  public TableView<FieldOfStudy> tblFieldOfStudy;
  @FXML
  public TableColumn<FieldOfStudy, Integer> tblColIdFieldOfStudy;
  @FXML
  public TableColumn<FieldOfStudy, String> tblColNameFieldOfStudy;
  @FXML
  public TextField tfNameFieldOfStudy;
  @FXML
  public Button btnAddFieldOfStudy;

  @FXML
  public TableView<MainField> tblMainField;
  @FXML
  public TableColumn<MainField, Integer> tblColIdMainField;
  @FXML
  public TableColumn<MainField, String> tblColNameMainField;
  @FXML
  public TextField tfNameMainField;
  @FXML
  public Button btnAddMainField;

  private Stage stage;

  private ProtocolService protocolService;
  private ProtocolMapper protocolMapper;

  private FieldOfStudyService fieldOfStudyService;
  private FieldOfStudyMapper fieldOfStudyMapper;

  private MainFieldService mainFieldService;
  private MainFieldMapper mainFieldMapper;

  private ObservableList<Protocol> protocols = FXCollections.observableArrayList();
  private ObservableList<FieldOfStudy> fieldOfStudies = FXCollections.observableArrayList();
  private ObservableList<MainField> mainFields = FXCollections.observableArrayList();

  @Autowired
  public FXMLSettingsController(ProtocolService protocolService,
      ProtocolMapper protocolMapper, FieldOfStudyService fieldOfStudyService,
      FieldOfStudyMapper fieldOfStudiesMapper, MainFieldService mainFieldService,
      MainFieldMapper mainFieldMapper) {
    this.protocolService = protocolService;
    this.protocolMapper = protocolMapper;
    this.fieldOfStudyService = fieldOfStudyService;
    this.fieldOfStudyMapper = fieldOfStudiesMapper;
    this.mainFieldService = mainFieldService;
    this.mainFieldMapper = mainFieldMapper;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    clearComponents();
    initializeTableProtocols();
    initializeTableFieldOfStudy();
    initializeTableMainField();
    fillTableProtocols();
    fillTableFieldOfStudy();
    fillTableMainField();
    btnAddProtocol.setOnAction(event -> {
      if (validateProtocolInputs()) addProtocol();
    });
    btnAddFieldOfStudy.setOnAction(event -> {
      if (validateFieldOfStudyInputs()) addFieldOfStudy();
    });
    btnAddMainField.setOnAction(event -> {
      if (validateMainFieldInputs()) addMainField();
    });
  }

  private void clearComponents() {
    protocols.clear();
    fieldOfStudies.clear();
    mainFields.clear();
  }

  //-------------------------------------------------------------------------------
  // PROTOCOLS
  //-------------------------------------------------------------------------------
  private void initializeTableProtocols() {
    tblColIdProtocol.setCellValueFactory(new PropertyValueFactory<>("id"));
    tblColNameUKProtocol.setCellValueFactory(new PropertyValueFactory<>("nameUK"));
    tblColNameENProtocol.setCellValueFactory(new PropertyValueFactory<>("nameEN"));

    tblColNameUKProtocol.setCellFactory(TextFieldTableCell.forTableColumn());
    tblColNameENProtocol.setCellFactory(TextFieldTableCell.forTableColumn());
    tblColNameUKProtocol.setOnEditCommit(event -> {
      event.getRowValue().setNameUK(event.getNewValue());
      updateProtocol(event.getRowValue());
    });
    tblColNameENProtocol.setOnEditCommit(event -> {
      event.getRowValue().setNameEN(event.getNewValue());
      updateProtocol(event.getRowValue());
    });

    tblProtocols.setRowFactory(
        tableView -> {
          final TableRow<Protocol> row = new TableRow<>();
          final ContextMenu rowMenu = new ContextMenu();
          final MenuItem removeItem = new MenuItem("Delete");
          removeItem.setOnAction(event -> removeProtocol(row.getItem()));
          rowMenu.getItems().addAll(removeItem);

          // only display context menu for non-null items:
          row.contextMenuProperty().bind(
              Bindings.when(Bindings.isNotNull(row.itemProperty()))
                  .then(rowMenu)
                  .otherwise((ContextMenu) null));
          return row;
        });
  }

  private void fillTableProtocols() {
    try {
      protocols.addAll(protocolMapper.map(protocolService.getAll()));
      tblProtocols.setItems(protocols);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void addProtocol() {
    final String nameUK = tfNameUKProtocol.getText().trim();
    final String nameEN = tfNameENProtocol.getText().trim();
    final Protocol protocol = new Protocol(protocols.size() + 1, nameUK, nameEN);

    try {
      if (protocolService.create(protocolMapper.reverseMap(protocol)) == 1) {
        protocols.add(protocol);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void updateProtocol(Protocol protocol) {
    try {
      protocolService.update(protocolMapper.reverseMap(protocol));
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void removeProtocol(Protocol item) {
    try {
      if (protocolService.delete(item.getId()) == 1) {
        tblProtocols.getItems().remove(item);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private boolean validateProtocolInputs() {
    boolean result = true;

    if (!Validation.validateTextField(tfNameUKProtocol)) {
      tfNameUKProtocol.setStyle(Validation.getTextFieldErrorStyle());
      tfNameUKProtocol.textProperty().addListener(e -> tfNameUKProtocol.setStyle(null));
      result = false;
    }

    if (!Validation.validateTextField(tfNameENProtocol)) {
      tfNameENProtocol.setStyle(Validation.getTextFieldErrorStyle());
      tfNameENProtocol.textProperty().addListener(e -> tfNameENProtocol.setStyle(null));
      result = false;
    }
    return result;
  }

  //-------------------------------------------------------------------------------
  // MAIN FIELD
  //-------------------------------------------------------------------------------
  private void initializeTableMainField() {
    tblColIdMainField.setCellValueFactory(new PropertyValueFactory<>("id"));
    tblColNameMainField.setCellValueFactory(new PropertyValueFactory<>("name"));

    tblColNameMainField.setCellFactory(TextFieldTableCell.forTableColumn());
    tblColNameMainField.setOnEditCommit(event -> {
      event.getRowValue().setName(event.getNewValue());
      updateMainField(event.getRowValue());
    });

    tblMainField.setRowFactory(
        tableView -> {
          final TableRow<MainField> row = new TableRow<>();
          final ContextMenu rowMenu = new ContextMenu();
          final MenuItem removeItem = new MenuItem("Delete");
          removeItem.setOnAction(event -> removeMainField(row.getItem()));
          rowMenu.getItems().addAll(removeItem);

          // only display context menu for non-null items:
          row.contextMenuProperty().bind(
              Bindings.when(Bindings.isNotNull(row.itemProperty()))
                  .then(rowMenu)
                  .otherwise((ContextMenu) null));
          return row;
        });
  }

  private void fillTableMainField() {
    try {
      mainFields.addAll(mainFieldMapper.map(mainFieldService.getAll()));
      tblMainField.setItems(mainFields);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void addMainField() {
    final String name = tfNameMainField.getText().trim();
    final MainField mainField = new MainField(mainFields.size() + 1, name);

    try {
      if (mainFieldService.create(mainFieldMapper.reverseMap(mainField)) == 1) {
        mainFields.add(mainField);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void updateMainField(MainField item) {
    try {
      mainFieldService.update(mainFieldMapper.reverseMap(item));
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void removeMainField(MainField item) {
    try {
      if (mainFieldService.delete(item.getId()) == 1) {
        tblMainField.getItems().remove(item);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private boolean validateMainFieldInputs() {
    boolean result = true;

    if (!Validation.validateTextField(tfNameMainField)) {
      tfNameMainField.setStyle(Validation.getTextFieldErrorStyle());
      tfNameMainField.textProperty().addListener(e -> tfNameFieldOfStudy.setStyle(null));
      result = false;
    }
    return result;
  }

  //-------------------------------------------------------------------------------
  // FIELD OF STUDY
  //-------------------------------------------------------------------------------
  private void initializeTableFieldOfStudy() {
    tblColIdFieldOfStudy.setCellValueFactory(new PropertyValueFactory<>("id"));
    tblColNameFieldOfStudy.setCellValueFactory(new PropertyValueFactory<>("name"));

    tblColNameFieldOfStudy.setCellFactory(TextFieldTableCell.forTableColumn());
    tblColNameFieldOfStudy.setOnEditCommit(event -> {
      event.getRowValue().setName(event.getNewValue());
      updateFieldOfStudy(event.getRowValue());
    });

    tblFieldOfStudy.setRowFactory(
        tableView -> {
          final TableRow<FieldOfStudy> row = new TableRow<>();
          final ContextMenu rowMenu = new ContextMenu();
          final MenuItem removeItem = new MenuItem("Delete");
          removeItem.setOnAction(event -> removeFieldOfStudy(row.getItem()));
          rowMenu.getItems().addAll(removeItem);

          // only display context menu for non-null items:
          row.contextMenuProperty().bind(
              Bindings.when(Bindings.isNotNull(row.itemProperty()))
                  .then(rowMenu)
                  .otherwise((ContextMenu) null));
          return row;
        });
  }

  private void fillTableFieldOfStudy() {
    try {
      fieldOfStudies.addAll(fieldOfStudyMapper.map(fieldOfStudyService.getAll()));
      tblFieldOfStudy.setItems(fieldOfStudies);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void addFieldOfStudy() {
    final String name = tfNameFieldOfStudy.getText().trim();
    final FieldOfStudy fieldOfStudy = new FieldOfStudy(fieldOfStudies.size() + 1, name);

    try {
      if (fieldOfStudyService.create(fieldOfStudyMapper.reverseMap(fieldOfStudy)) == 1) {
        fieldOfStudies.add(fieldOfStudy);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void updateFieldOfStudy(FieldOfStudy item) {
    try {
      fieldOfStudyService.update(fieldOfStudyMapper.reverseMap(item));
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void removeFieldOfStudy(FieldOfStudy item) {
    try {
      if (fieldOfStudyService.delete(item.getId()) == 1) {
        tblFieldOfStudy.getItems().remove(item);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private boolean validateFieldOfStudyInputs() {
    boolean result = true;

    if (!Validation.validateTextField(tfNameFieldOfStudy)) {
      tfNameFieldOfStudy.setStyle(Validation.getTextFieldErrorStyle());
      tfNameFieldOfStudy.textProperty().addListener(e -> tfNameFieldOfStudy.setStyle(null));
      result = false;
    }
    return result;
  }

  void display() throws Exception {
    final Parent root = SpringFXMLLoader.create()
        .applicationContext(Main.getContext())
        .location(FXMLStudentController.class
            .getResource("../../fxml/settings.fxml"))
        .load();

    final Scene scene = new Scene(root);

    stage = new Stage();
    stage.setScene(scene);

    stage.setTitle("Налаштування");

    //setting up min width & height parameters for window
    stage.setMinWidth(600);
    stage.setMinHeight(450);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

  private void closeWindow() {
    stage.close();
  }
}
