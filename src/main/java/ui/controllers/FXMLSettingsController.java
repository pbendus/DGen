package ui.controllers;

import db.mapper.DurationOfStudyMapper;
import db.mapper.DurationOfTrainingMapper;
import db.mapper.EctsCreditsMapper;
import db.mapper.FieldOfStudyMapper;
import db.mapper.GroupMapper;
import db.mapper.MainFieldMapper;
import db.mapper.ModeOfStudyMapper;
import db.mapper.OfficialDurationOfProgrammeMapper;
import db.mapper.ProtocolMapper;
import db.services.DurationOfStudyService;
import db.services.DurationOfTrainingService;
import db.services.EctsCreditsService;
import db.services.FieldOfStudyService;
import db.services.GroupService;
import db.services.MainFieldService;
import db.services.ModeOfStudyService;
import db.services.OfficialDurationOfProgrammeService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.DurationOfStudy;
import ui.models.DurationOfTraining;
import ui.models.EctsCredits;
import ui.models.FieldOfStudy;
import ui.models.Group;
import ui.models.MainField;
import ui.models.ModeOfStudy;
import ui.models.OfficialDurationOfProgramme;
import ui.models.Protocol;
import ui.utils.SpringFXMLLoader;
import ui.utils.Validation;

@Controller("fxmlSettingsController")
public class FXMLSettingsController implements Initializable {

  private static final Logger LOGGER = LogManager.getLogger();

  @FXML
  public TabPane tabPane;
  @FXML
  public javafx.scene.control.Tab tabDurationOfTraining;
  @FXML
  public javafx.scene.control.Tab tabOfficialDuration;
  @FXML
  public javafx.scene.control.Tab tabGroups;
  @FXML
  public javafx.scene.control.Tab tabMainField;
  @FXML
  public javafx.scene.control.Tab tabFieldOfStudy;
  @FXML
  public javafx.scene.control.Tab tabProtocols;
  @FXML
  public javafx.scene.control.Tab tabEctsCredits;
  @FXML
  public javafx.scene.control.Tab tabAccessRequirements;

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

  @FXML
  public TableView<Group> tblGroups;
  @FXML
  public TableColumn<Group, Integer> tblColIdGroups;
  @FXML
  public TableColumn<Group, String> tblColNameGroups;
  @FXML
  public TextField tfNameGroups;
  @FXML
  public Button btnAddGroups;

  @FXML
  public TableView<OfficialDurationOfProgramme> tblOfficialDuration;
  @FXML
  public TableColumn<OfficialDurationOfProgramme, Integer> tblColIdOfficialDuration;
  @FXML
  public TableColumn<OfficialDurationOfProgramme, String> tblColNameOfficialDuration;
  @FXML
  public TableColumn<OfficialDurationOfProgramme, ModeOfStudy> tblColModeOfStudyOD;
  @FXML
  public TableColumn<OfficialDurationOfProgramme, DurationOfStudy> tblColDurationOfStudyOD;
  @FXML
  public TextField tfNameOfficialDuration;
  @FXML
  public ComboBox<ModeOfStudy> cbModeOfStudyOD;
  @FXML
  public ComboBox<DurationOfStudy> cbDurationOfStudyOD;
  @FXML
  public Button btnAddOfficialDuration;

  @FXML
  public TableView<DurationOfTraining> tblDurationOfTraining;
  @FXML
  public TableColumn<DurationOfTraining, Integer> tblColIdDurationOfTraining;
  @FXML
  public TableColumn<DurationOfTraining, String> tblColNameDurationOfTraining;
  @FXML
  public TableColumn<DurationOfTraining, ModeOfStudy> tblColModeOfStudyDoT;
  @FXML
  public TableColumn<DurationOfTraining, DurationOfStudy> tblColDurationOfStudyDoT;
  @FXML
  public TextField tfNameDurationOfTraining;
  @FXML
  public ComboBox<ModeOfStudy> cbModeOfStudyDoT;
  @FXML
  public ComboBox<DurationOfStudy> cbDurationOfStudyDoT;
  @FXML
  public Button btnAddDurationOfTraining;

  @FXML
  public TableView<EctsCredits> tblEctsCredits;
  @FXML
  public TableColumn<EctsCredits, Integer> tblColIdEctsCredits;
  @FXML
  public TableColumn<EctsCredits, String> tblColNameEctsCredits;
  @FXML
  public TableColumn<EctsCredits, DurationOfStudy> tblColDurationOfStudyEctsCredits;
  @FXML
  public TextField tfNameEctsCredits;
  @FXML
  public Button btnAddEctsCredits;
  @FXML
  public ComboBox<DurationOfStudy> cbDurationOfStudyEctsCredits;

  @FXML
  public TableView tblAccessRequirements;
  @FXML
  public TableColumn tblColIdAccessRequirements;
  @FXML
  public TableColumn tblColNameAccessRequirements;
  @FXML
  public TableColumn tblColDurationOfStudyAR;
  @FXML
  public TextField tfNameAccessRequirements;
  @FXML
  public Button btnAddAccessRequirements;
  @FXML
  public ComboBox cbDurationOfStudyAR;

  private Stage stage;

  private ProtocolService protocolService;
  private ProtocolMapper protocolMapper;

  private FieldOfStudyService fieldOfStudyService;
  private FieldOfStudyMapper fieldOfStudyMapper;

  private MainFieldService mainFieldService;
  private MainFieldMapper mainFieldMapper;

  private GroupService groupService;
  private GroupMapper groupMapper;

  private OfficialDurationOfProgrammeService officialDurationOfProgrammeService;
  private OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper;

  private DurationOfStudyService durationOfStudyService;
  private DurationOfStudyMapper durationOfStudyMapper;

  private DurationOfTrainingService durationOfTrainingService;
  private DurationOfTrainingMapper durationOfTrainingMapper;

  private ModeOfStudyService modeOfStudyService;
  private ModeOfStudyMapper modeOfStudyMapper;

  private EctsCreditsService ectsCreditsService;
  private EctsCreditsMapper ectsCreditsMapper;

  private ObservableList<Protocol> protocols = FXCollections.observableArrayList();
  private ObservableList<FieldOfStudy> fieldOfStudies = FXCollections.observableArrayList();
  private ObservableList<MainField> mainFields = FXCollections.observableArrayList();
  private ObservableList<Group> groups = FXCollections.observableArrayList();
  private ObservableList<OfficialDurationOfProgramme> officialDurationOfProgrammes =
      FXCollections.observableArrayList();
  private ObservableList<DurationOfStudy> durationOfStudies = FXCollections.observableArrayList();
  private ObservableList<DurationOfTraining> durationOfTrainings =
      FXCollections.observableArrayList();
  private ObservableList<ModeOfStudy> modeOfStudies = FXCollections.observableArrayList();
  private ObservableList<EctsCredits> ectsCredits = FXCollections.observableArrayList();

  private Tab tab;

  @Autowired
  public FXMLSettingsController(ProtocolService protocolService,
      ProtocolMapper protocolMapper, FieldOfStudyService fieldOfStudyService,
      FieldOfStudyMapper fieldOfStudiesMapper, MainFieldService mainFieldService,
      MainFieldMapper mainFieldMapper, GroupService groupService, GroupMapper groupMapper,
      OfficialDurationOfProgrammeService officialDurationOfProgrammeService,
      OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper,
      DurationOfStudyService durationOfStudyService,
      DurationOfStudyMapper durationOfStudyMapper,
      DurationOfTrainingService durationOfTrainingService,
      DurationOfTrainingMapper durationOfTrainingMapper,
      ModeOfStudyService modeOfStudyService, ModeOfStudyMapper modeOfStudyMapper,
      EctsCreditsService ectsCreditsService, EctsCreditsMapper ectsCreditsMapper) {
    this.protocolService = protocolService;
    this.protocolMapper = protocolMapper;
    this.fieldOfStudyService = fieldOfStudyService;
    this.fieldOfStudyMapper = fieldOfStudiesMapper;
    this.mainFieldService = mainFieldService;
    this.mainFieldMapper = mainFieldMapper;
    this.groupService = groupService;
    this.groupMapper = groupMapper;
    this.officialDurationOfProgrammeService = officialDurationOfProgrammeService;
    this.officialDurationOfProgrammeMapper = officialDurationOfProgrammeMapper;
    this.durationOfStudyService = durationOfStudyService;
    this.durationOfStudyMapper = durationOfStudyMapper;
    this.durationOfTrainingService = durationOfTrainingService;
    this.durationOfTrainingMapper = durationOfTrainingMapper;
    this.modeOfStudyService = modeOfStudyService;
    this.modeOfStudyMapper = modeOfStudyMapper;
    this.ectsCreditsService = ectsCreditsService;
    this.ectsCreditsMapper = ectsCreditsMapper;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    selectTab();
    clearComponents();
    initializeTableProtocols();
    initializeTableFieldOfStudy();
    initializeTableMainField();
    initializeTableGroups();
    initializeTableOfficialDuration();
    initializeTableDurationOfTraining();

    fillModeOfStudies();
    fillDurationOfStudies();

    fillTableProtocols();
    fillTableFieldOfStudy();
    fillTableMainField();
    fillTableGroups();
    fillTableOfficialDuration();
    fillTableDurationOfTraining();

    btnAddProtocol.setOnAction(event -> {
      if (validateProtocolInputs()) addProtocol();
    });
    btnAddFieldOfStudy.setOnAction(event -> {
      if (validateFieldOfStudyInputs()) addFieldOfStudy();
    });
    btnAddMainField.setOnAction(event -> {
      if (validateMainFieldInputs()) addMainField();
    });
    btnAddGroups.setOnAction(event -> {
      if (validateGroupInputs()) addGroup();
    });
    btnAddOfficialDuration.setOnAction(event -> {
      if (validateOfficialDurationInputs()) addOfficialDuration();
    });
    btnAddDurationOfTraining.setOnAction(event -> {
      if (validateDurationOfTrainingInputs()) addDurationOfTraining();
    });
  }

  private void selectTab() {
    switch (tab) {
      default:
      case PROTOCOLS:
        tabPane.getSelectionModel().select(tabProtocols);
        break;
      case GROUPS:
        tabPane.getSelectionModel().select(tabGroups);
        break;
      case MAIN_FIELD:
        tabPane.getSelectionModel().select(tabMainField);
        break;
      case FIELD_OF_STUDY:
        tabPane.getSelectionModel().select(tabFieldOfStudy);
        break;
      case DURATION_OF_TRAINING:
        tabPane.getSelectionModel().select(tabDurationOfTraining);
        break;
      case OFFICIAL_DURATION:
        tabPane.getSelectionModel().select(tabOfficialDuration);
        break;
      case ECTS_CREDITS:
        tabPane.getSelectionModel().select(tabEctsCredits);
        break;
      case ACCESS_REQUIREMENTS:
        tabPane.getSelectionModel().select(tabAccessRequirements);
        break;
    }
  }

  private void clearComponents() {
    protocols.clear();
    fieldOfStudies.clear();
    mainFields.clear();
    groups.clear();
    officialDurationOfProgrammes.clear();
    durationOfStudies.clear();
    durationOfTrainings.clear();
    modeOfStudies.clear();
    ectsCredits.clear();
  }

  private void fillDurationOfStudies() {
    try {
      durationOfStudies.addAll(durationOfStudyMapper.map(durationOfStudyService.getAll()));
      cbDurationOfStudyDoT.getItems().addAll(durationOfStudies);
      cbDurationOfStudyOD.getItems().addAll(durationOfStudies);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void fillModeOfStudies() {
    try {
      modeOfStudies.addAll(modeOfStudyMapper.map(modeOfStudyService.getAll()));
      cbModeOfStudyDoT.getItems().addAll(modeOfStudies);
      cbModeOfStudyOD.getItems().addAll(modeOfStudies);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
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
        protocols.add(protocolMapper.map(protocolService.getByName(nameEN, nameUK)));
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
        mainFields.add(mainFieldMapper.map(mainFieldService.getByName(name)));
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
        fieldOfStudies.add(fieldOfStudyMapper.map(fieldOfStudyService.getByName(name)));
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

  //-------------------------------------------------------------------------------
  // GROUPS
  //-------------------------------------------------------------------------------
  private void initializeTableGroups() {
    tblColIdGroups.setCellValueFactory(new PropertyValueFactory<>("id"));
    tblColNameGroups.setCellValueFactory(new PropertyValueFactory<>("name"));

    tblColNameGroups.setCellFactory(TextFieldTableCell.forTableColumn());
    tblColNameGroups.setOnEditCommit(event -> {
      event.getRowValue().setName(event.getNewValue());
      updateGroup(event.getRowValue());
    });

    tblGroups.setRowFactory(
        tableView -> {
          final TableRow<Group> row = new TableRow<>();
          final ContextMenu rowMenu = new ContextMenu();
          final MenuItem removeItem = new MenuItem("Delete");
          removeItem.setOnAction(event -> removeGroup(row.getItem()));
          rowMenu.getItems().addAll(removeItem);

          // only display context menu for non-null items:
          row.contextMenuProperty().bind(
              Bindings.when(Bindings.isNotNull(row.itemProperty()))
                  .then(rowMenu)
                  .otherwise((ContextMenu) null));
          return row;
        });
  }

  private void fillTableGroups() {
    try {
      groups.addAll(groupMapper.map(groupService.getAll()));
      tblGroups.setItems(groups);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void addGroup() {
    final String name = tfNameGroups.getText().trim();
    final Group group = new Group(groups.size() + 1, name);

    try {
      if (groupService.create(groupMapper.reverseMap(group)) == 1) {
        groups.add(groupMapper.map(groupService.getByName(name)));
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void updateGroup(Group item) {
    try {
      groupService.update(groupMapper.reverseMap(item));
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void removeGroup(Group item) {
    try {
      if (groupService.delete(item.getId()) == 1) {
        tblGroups.getItems().remove(item);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private boolean validateGroupInputs() {
    boolean result = true;

    if (!Validation.validateTextField(tfNameGroups)) {
      tfNameGroups.setStyle(Validation.getTextFieldErrorStyle());
      tfNameGroups.textProperty().addListener(e -> tfNameGroups.setStyle(null));
      result = false;
    }
    return result;
  }

  //-------------------------------------------------------------------------------
  // OFFICIAL DURATION OF PROGRAMME
  //-------------------------------------------------------------------------------
  private void initializeTableOfficialDuration() {
    tblColIdOfficialDuration.setCellValueFactory(new PropertyValueFactory<>("id"));
    tblColNameOfficialDuration.setCellValueFactory(new PropertyValueFactory<>("name"));
    tblColDurationOfStudyOD.setCellValueFactory(
        param -> param.getValue().durationOfStudyProperty());
    tblColDurationOfStudyOD.setCellFactory(ComboBoxTableCell.forTableColumn(durationOfStudies));
    tblColDurationOfStudyOD.setOnEditCommit(event -> {
      event.getRowValue().setDurationOfStudy(event.getNewValue());
      updateOfficialDuration(event.getRowValue());
    });

    tblColModeOfStudyOD.setCellValueFactory(param -> param.getValue().modeOfStudyProperty());
    tblColModeOfStudyOD.setCellFactory(ComboBoxTableCell.forTableColumn(modeOfStudies));
    tblColModeOfStudyOD.setOnEditCommit(event -> {
      event.getRowValue().setModeOfStudy(event.getNewValue());
      updateOfficialDuration(event.getRowValue());
    });

    tblColNameOfficialDuration.setCellFactory(TextFieldTableCell.forTableColumn());
    tblColNameOfficialDuration.setOnEditCommit(event -> {
      event.getRowValue().setName(event.getNewValue());
      updateOfficialDuration(event.getRowValue());
    });

    tblOfficialDuration.setRowFactory(
        tableView -> {
          final TableRow<OfficialDurationOfProgramme> row = new TableRow<>();
          final ContextMenu rowMenu = new ContextMenu();
          final MenuItem removeItem = new MenuItem("Delete");
          removeItem.setOnAction(event -> removeOfficialDuration(row.getItem()));
          rowMenu.getItems().addAll(removeItem);

          // only display context menu for non-null items:
          row.contextMenuProperty().bind(
              Bindings.when(Bindings.isNotNull(row.itemProperty()))
                  .then(rowMenu)
                  .otherwise((ContextMenu) null));
          return row;
        });
  }

  private void fillTableOfficialDuration() {
    try {
      officialDurationOfProgrammes.addAll(
          officialDurationOfProgrammeMapper.map(officialDurationOfProgrammeService.getAll()));
      tblOfficialDuration.setItems(officialDurationOfProgrammes);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void addOfficialDuration() {
    final String name = tfNameOfficialDuration.getText().trim();
    final ModeOfStudy modeOfStudy = cbModeOfStudyOD.getSelectionModel().getSelectedItem();
    final DurationOfStudy durationOfStudy =
        cbDurationOfStudyOD.getSelectionModel().getSelectedItem();
    final OfficialDurationOfProgramme officialDurationOfProgramme =
        new OfficialDurationOfProgramme(officialDurationOfProgrammes.size() + 1, name, modeOfStudy,
            durationOfStudy);

    try {
      if (officialDurationOfProgrammeService.create(
          officialDurationOfProgrammeMapper.reverseMap(officialDurationOfProgramme)) == 1) {
        officialDurationOfProgrammes.add(officialDurationOfProgrammeMapper.map(
            officialDurationOfProgrammeService.getByName(name)));
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void updateOfficialDuration(OfficialDurationOfProgramme item) {
    try {
      officialDurationOfProgrammeService.update(officialDurationOfProgrammeMapper.reverseMap(item));
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void removeOfficialDuration(OfficialDurationOfProgramme item) {
    try {
      if (officialDurationOfProgrammeService.delete(item.getId()) == 1) {
        tblOfficialDuration.getItems().remove(item);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private boolean validateOfficialDurationInputs() {
    boolean result = true;

    if (!Validation.validateTextField(tfNameOfficialDuration)) {
      tfNameOfficialDuration.setStyle(Validation.getTextFieldErrorStyle());
      tfNameOfficialDuration.textProperty().addListener(e -> tfNameOfficialDuration.setStyle(null));
      result = false;
    }

    if (!Validation.validateComboBox(cbModeOfStudyOD)) {
      cbModeOfStudyOD.setStyle(Validation.getComboBoxErrorStyle());
      cbModeOfStudyOD.valueProperty().addListener(e -> cbModeOfStudyOD.setStyle(null));
      result = false;
    }

    if (!Validation.validateComboBox(cbDurationOfStudyOD)) {
      cbDurationOfStudyOD.setStyle(Validation.getComboBoxErrorStyle());
      cbDurationOfStudyOD.valueProperty().addListener(e -> cbDurationOfStudyOD.setStyle(null));
      result = false;
    }
    return result;
  }

  //-------------------------------------------------------------------------------
  // DURATION OF TRAINING
  //-------------------------------------------------------------------------------
  private void initializeTableDurationOfTraining() {
    tblColIdDurationOfTraining.setCellValueFactory(new PropertyValueFactory<>("id"));
    tblColNameDurationOfTraining.setCellValueFactory(new PropertyValueFactory<>("name"));
    tblColDurationOfStudyDoT.setCellValueFactory(
        param -> param.getValue().durationOfStudyProperty());
    tblColDurationOfStudyDoT.setCellFactory(ComboBoxTableCell.forTableColumn(durationOfStudies));
    tblColDurationOfStudyDoT.setOnEditCommit(event -> {
      event.getRowValue().setDurationOfStudy(event.getNewValue());
      updateDurationOfTraining(event.getRowValue());
    });

    tblColModeOfStudyDoT.setCellValueFactory(param -> param.getValue().modeOfStudyProperty());
    tblColModeOfStudyDoT.setCellFactory(ComboBoxTableCell.forTableColumn(modeOfStudies));
    tblColModeOfStudyDoT.setOnEditCommit(event -> {
      event.getRowValue().setModeOfStudy(event.getNewValue());
      updateDurationOfTraining(event.getRowValue());
    });

    tblColNameDurationOfTraining.setCellFactory(TextFieldTableCell.forTableColumn());
    tblColNameDurationOfTraining.setOnEditCommit(event -> {
      event.getRowValue().setName(event.getNewValue());
      updateDurationOfTraining(event.getRowValue());
    });

    tblDurationOfTraining.setRowFactory(
        tableView -> {
          final TableRow<DurationOfTraining> row = new TableRow<>();
          final ContextMenu rowMenu = new ContextMenu();
          final MenuItem removeItem = new MenuItem("Delete");
          removeItem.setOnAction(event -> removeDurationOfTraining(row.getItem()));
          rowMenu.getItems().addAll(removeItem);

          // only display context menu for non-null items:
          row.contextMenuProperty().bind(
              Bindings.when(Bindings.isNotNull(row.itemProperty()))
                  .then(rowMenu)
                  .otherwise((ContextMenu) null));
          return row;
        });
  }

  private void fillTableDurationOfTraining() {
    try {
      durationOfTrainings.addAll(
          durationOfTrainingMapper.map(durationOfTrainingService.getAll()));
      tblDurationOfTraining.setItems(durationOfTrainings);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void addDurationOfTraining() {
    final String name = tfNameDurationOfTraining.getText().trim();
    final ModeOfStudy modeOfStudy = cbModeOfStudyDoT.getSelectionModel().getSelectedItem();
    final DurationOfStudy durationOfStudy =
        cbDurationOfStudyDoT.getSelectionModel().getSelectedItem();
    final DurationOfTraining durationOfTraining =
        new DurationOfTraining(durationOfTrainings.size() + 1, name, modeOfStudy,
            durationOfStudy);

    try {
      if (durationOfTrainingService.create(
          durationOfTrainingMapper.reverseMap(durationOfTraining)) == 1) {
        durationOfTrainings.add(
            durationOfTrainingMapper.map(durationOfTrainingService.getByName(name)));
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void updateDurationOfTraining(DurationOfTraining item) {
    try {
      durationOfTrainingService.update(durationOfTrainingMapper.reverseMap(item));
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private void removeDurationOfTraining(DurationOfTraining item) {
    try {
      if (durationOfTrainingService.delete(item.getId()) == 1) {
        tblDurationOfTraining.getItems().remove(item);
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private boolean validateDurationOfTrainingInputs() {
    boolean result = true;

    if (!Validation.validateTextField(tfNameDurationOfTraining)) {
      tfNameDurationOfTraining.setStyle(Validation.getTextFieldErrorStyle());
      tfNameDurationOfTraining.textProperty()
          .addListener(e -> tfNameDurationOfTraining.setStyle(null));
      result = false;
    }

    if (!Validation.validateComboBox(cbModeOfStudyDoT)) {
      cbModeOfStudyDoT.setStyle(Validation.getComboBoxErrorStyle());
      cbModeOfStudyDoT.valueProperty().addListener(e -> cbModeOfStudyDoT.setStyle(null));
      result = false;
    }

    if (!Validation.validateComboBox(cbDurationOfStudyDoT)) {
      cbDurationOfStudyDoT.setStyle(Validation.getComboBoxErrorStyle());
      cbDurationOfStudyDoT.valueProperty().addListener(e -> cbDurationOfStudyDoT.setStyle(null));
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

  public void setTab(Tab tab) {
    this.tab = tab;
  }

  public enum Tab {
    PROTOCOLS, MAIN_FIELD, FIELD_OF_STUDY, GROUPS, OFFICIAL_DURATION,
    DURATION_OF_TRAINING, ECTS_CREDITS, ACCESS_REQUIREMENTS
  }
}
