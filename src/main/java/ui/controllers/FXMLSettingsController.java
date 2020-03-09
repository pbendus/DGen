package ui.controllers;

import db.mappers.*;
import db.services.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.*;
import ui.utils.AlertBox;
import ui.utils.SpringFXMLLoader;
import ui.utils.Validation;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    public javafx.scene.control.Tab tabEducationalTemplate;

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
    public TableView<AccessRequirements> tblAccessRequirements;
    @FXML
    public TableColumn<AccessRequirements, Integer> tblColIdAccessRequirements;
    @FXML
    public TableColumn<AccessRequirements, String> tblColNameAccessRequirements;
    @FXML
    public TextField tfNameAccessRequirements;
    @FXML
    public Button btnAddAccessRequirements;

    @FXML
    public TableView<EducationalComponentTemplate> tblEducationalTemplate;
    @FXML
    public TableColumn<EducationalComponentTemplate, Integer> tblColIdEducationalTemplate;
    @FXML
    public TableColumn<EducationalComponentTemplate, String> tblColNameEducationalTemplate;
    @FXML
    public TableColumn<EducationalComponentTemplate, EducationalComponentType> tblColTypeEducationalTemplate;
    @FXML
    public TableColumn<EducationalComponentTemplate, Double> tblColCreditsEducationalTemplate;
    @FXML
    public TextField tfNameEducationalTemplate;
    @FXML
    public Button btnAddEducationalTemplate;
    @FXML
    public ComboBox<EducationalComponentType> cbTypeEducationalTemplate;
    @FXML
    public TextField tfCreditsEducationalTemplate;

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

    private AccessRequirementsService accessRequirementsService;
    private AccessRequirementsMapper accessRequirementsMapper;

    private EducationalComponentTemplateService educationalComponentTemplateService;
    private EducationalComponentTemplateMapper educationalComponentTemplateMapper;

    private EducationalComponentTypeService educationalComponentTypeService;
    private EducationalComponentService educationalComponentService;
    private EducationalComponentTypeMapper educationalComponentTypeMapper;

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
    private ObservableList<AccessRequirements> accessRequirements = FXCollections.observableArrayList();
    private ObservableList<EducationalComponentTemplate> educationalComponentTemplates = FXCollections.observableArrayList();
    private ObservableList<EducationalComponentType> educationalComponentTypes = FXCollections.observableArrayList();

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
                                  EctsCreditsService ectsCreditsService, EctsCreditsMapper ectsCreditsMapper,
                                  AccessRequirementsService accessRequirementsService,
                                  AccessRequirementsMapper accessRequirementsMapper,
                                  EducationalComponentTemplateService educationalComponentTemplateService,
                                  EducationalComponentTemplateMapper educationalComponentTemplateMapper,
                                  EducationalComponentTypeService educationalComponentTypeService,
                                  EducationalComponentService educationalComponentService,
                                  EducationalComponentTypeMapper educationalComponentTypeMapper) {
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
        this.accessRequirementsService = accessRequirementsService;
        this.accessRequirementsMapper = accessRequirementsMapper;
        this.educationalComponentTemplateService = educationalComponentTemplateService;
        this.educationalComponentTemplateMapper = educationalComponentTemplateMapper;
        this.educationalComponentTypeService = educationalComponentTypeService;
        this.educationalComponentService = educationalComponentService;
        this.educationalComponentTypeMapper = educationalComponentTypeMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL location, ResourceBundle resources) {
        selectTab();
        clearComponents(protocols, fieldOfStudies, mainFields, groups, officialDurationOfProgrammes,
                officialDurationOfProgrammes, durationOfStudies, durationOfTrainings,
                modeOfStudies, ectsCredits, accessRequirements, educationalComponentTemplates, educationalComponentTypes);
        initializeTableProtocols();
        initializeTableFieldOfStudy();
        initializeTableMainField();
        initializeTableGroups();
        initializeTableOfficialDuration();
        initializeTableDurationOfTraining();
        initializeTableEctsCredits();
        initializeTableAccessRequirements();
        initializeTableEducationalTemplate();

        Helper.fillComboBoxes(durationOfStudies, durationOfStudyMapper, durationOfStudyService, cbDurationOfStudyDoT,
                cbDurationOfStudyOD, cbDurationOfStudyEctsCredits);
        Helper.fillComboBoxes(modeOfStudies, modeOfStudyMapper, modeOfStudyService, cbModeOfStudyDoT, cbModeOfStudyOD);
        Helper.fillComboBoxes(educationalComponentTypes, educationalComponentTypeMapper, educationalComponentTypeService,
                cbTypeEducationalTemplate);

        Helper.fillTable(protocols, protocolMapper, protocolService, tblProtocols);
        Helper.fillTable(fieldOfStudies, fieldOfStudyMapper, fieldOfStudyService, tblFieldOfStudy);
        Helper.fillTable(mainFields, mainFieldMapper, mainFieldService, tblMainField);
        Helper.fillTable(groups, groupMapper, groupService, tblGroups);
        Helper.fillTable(officialDurationOfProgrammes, officialDurationOfProgrammeMapper,
                officialDurationOfProgrammeService, tblOfficialDuration);
        Helper.fillTable(durationOfTrainings, durationOfTrainingMapper, durationOfTrainingService,
                tblDurationOfTraining);
        Helper.fillTable(ectsCredits, ectsCreditsMapper, ectsCreditsService, tblEctsCredits);
        Helper.fillTable(accessRequirements, accessRequirementsMapper, accessRequirementsService, tblAccessRequirements);
        Helper.fillTable(educationalComponentTemplates, educationalComponentTemplateMapper,
                educationalComponentTemplateService, tblEducationalTemplate);

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
        btnAddEctsCredits.setOnAction(event -> {
            if (validateEctsCreditsInputs()) addEctsCredits();
        });
        btnAddAccessRequirements.setOnAction(event -> {
            if (validateAccessRequirementsInputs()) addAccessRequirements();
        });
        btnAddEducationalTemplate.setOnAction(event -> {
            if (validateEducationalTemplateInputs()) addEducationalTemplate();
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
            case EDUCATIONAL_TEMPLATE:
                tabPane.getSelectionModel().select(tabEducationalTemplate);
                break;
        }
    }

    private void clearComponents(ObservableList... lists) {
        for (ObservableList list :
                lists) {
            list.clear();
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
            Helper.updateItem(event.getRowValue(), protocolMapper, protocolService);
        });
        tblColNameENProtocol.setOnEditCommit(event -> {
            event.getRowValue().setNameEN(event.getNewValue());
            Helper.updateItem(event.getRowValue(), protocolMapper, protocolService);
        });

        tblProtocols.setRowFactory(
                tableView -> {
                    final TableRow<Protocol> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> Helper.removeItem(row.getItem().getId(), row.getItem(),
                            protocolService, tblProtocols));
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    private void addProtocol() {
        final String nameUK = tfNameUKProtocol.getText().trim();
        final String nameEN = tfNameENProtocol.getText().trim();
        final Protocol protocol = new Protocol(protocols.size() + 1, nameUK, nameEN);

        Helper.insertItem(protocols, protocol, nameEN, protocolMapper, protocolService);
    }

    private boolean validateProtocolInputs() {
        return Validation.checkData(tfNameUKProtocol, tfNameENProtocol);
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
            Helper.updateItem(event.getRowValue(), mainFieldMapper, mainFieldService);
        });

        tblMainField.setRowFactory(
                tableView -> {
                    final TableRow<MainField> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> Helper.removeItem(row.getItem().getId(), row.getItem(),
                            mainFieldService, tblMainField));
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    private void addMainField() {
        final String name = tfNameMainField.getText().trim();
        final MainField mainField = new MainField(mainFields.size() + 1, name);

        Helper.insertItem(mainFields, mainField, name, mainFieldMapper, mainFieldService);
    }

    private boolean validateMainFieldInputs() {
        return Validation.checkData(tfNameMainField);
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
            Helper.updateItem(event.getRowValue(), fieldOfStudyMapper, fieldOfStudyService);
        });

        tblFieldOfStudy.setRowFactory(
                tableView -> {
                    final TableRow<FieldOfStudy> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> Helper.removeItem(row.getItem().getId(), row.getItem(),
                            fieldOfStudyService, tblFieldOfStudy));
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    private void addFieldOfStudy() {
        final String name = tfNameFieldOfStudy.getText().trim();
        final FieldOfStudy fieldOfStudy = new FieldOfStudy(fieldOfStudies.size() + 1, name);

        Helper.insertItem(fieldOfStudies, fieldOfStudy, name, fieldOfStudyMapper, fieldOfStudyService);
    }

    private boolean validateFieldOfStudyInputs() {
        return Validation.checkData(tfNameFieldOfStudy);
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
            Helper.updateItem(event.getRowValue(), groupMapper, groupService);
        });

        tblGroups.setRowFactory(
                tableView -> {
                    final TableRow<Group> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> Helper.removeItem(row.getItem().getId(), row.getItem(),
                            groupService, tblGroups));
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    private void addGroup() {
        final String name = tfNameGroups.getText().trim();
        final Group group = new Group(groups.size() + 1, name);

        Helper.insertItem(groups, group, name, groupMapper, groupService);
    }

    private boolean validateGroupInputs() {
        return Validation.checkData(tfNameGroups);
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
            Helper.updateItem(event.getRowValue(), officialDurationOfProgrammeMapper, officialDurationOfProgrammeService);
        });

        tblColModeOfStudyOD.setCellValueFactory(param -> param.getValue().modeOfStudyProperty());
        tblColModeOfStudyOD.setCellFactory(ComboBoxTableCell.forTableColumn(modeOfStudies));
        tblColModeOfStudyOD.setOnEditCommit(event -> {
            event.getRowValue().setModeOfStudy(event.getNewValue());
            Helper.updateItem(event.getRowValue(), officialDurationOfProgrammeMapper, officialDurationOfProgrammeService);
        });

        tblColNameOfficialDuration.setCellFactory(TextFieldTableCell.forTableColumn());
        tblColNameOfficialDuration.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
            Helper.updateItem(event.getRowValue(), officialDurationOfProgrammeMapper, officialDurationOfProgrammeService);
        });

        tblOfficialDuration.setRowFactory(
                tableView -> {
                    final TableRow<OfficialDurationOfProgramme> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> Helper.removeItem(row.getItem().getId(), row.getItem(),
                            officialDurationOfProgrammeService, tblOfficialDuration));
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    private void addOfficialDuration() {
        final String name = tfNameOfficialDuration.getText().trim();
        final ModeOfStudy modeOfStudy = cbModeOfStudyOD.getSelectionModel().getSelectedItem();
        final DurationOfStudy durationOfStudy =
                cbDurationOfStudyOD.getSelectionModel().getSelectedItem();
        final OfficialDurationOfProgramme officialDurationOfProgramme =
                new OfficialDurationOfProgramme(officialDurationOfProgrammes.size() + 1, name, modeOfStudy,
                        durationOfStudy);

        Helper.insertItem(officialDurationOfProgrammes, officialDurationOfProgramme, name,
                officialDurationOfProgrammeMapper, officialDurationOfProgrammeService);
    }

    private boolean validateOfficialDurationInputs() {
        return Validation.checkData(tfNameOfficialDuration) && Validation.checkData(cbModeOfStudyOD,
                cbDurationOfStudyOD);
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
            Helper.updateItem(event.getRowValue(), durationOfTrainingMapper, durationOfTrainingService);
        });

        tblColModeOfStudyDoT.setCellValueFactory(param -> param.getValue().modeOfStudyProperty());
        tblColModeOfStudyDoT.setCellFactory(ComboBoxTableCell.forTableColumn(modeOfStudies));
        tblColModeOfStudyDoT.setOnEditCommit(event -> {
            event.getRowValue().setModeOfStudy(event.getNewValue());
            Helper.updateItem(event.getRowValue(), durationOfTrainingMapper, durationOfTrainingService);
        });

        tblColNameDurationOfTraining.setCellFactory(TextFieldTableCell.forTableColumn());
        tblColNameDurationOfTraining.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
            Helper.updateItem(event.getRowValue(), durationOfTrainingMapper, durationOfTrainingService);
        });

        tblDurationOfTraining.setRowFactory(
                tableView -> {
                    final TableRow<DurationOfTraining> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> Helper.removeItem(row.getItem().getId(), row.getItem(),
                            durationOfTrainingService, tblDurationOfTraining));
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    private void addDurationOfTraining() {
        final String name = tfNameDurationOfTraining.getText().trim();
        final ModeOfStudy modeOfStudy = cbModeOfStudyDoT.getSelectionModel().getSelectedItem();
        final DurationOfStudy durationOfStudy =
                cbDurationOfStudyDoT.getSelectionModel().getSelectedItem();
        final DurationOfTraining durationOfTraining =
                new DurationOfTraining(durationOfTrainings.size() + 1, name, modeOfStudy,
                        durationOfStudy);

        Helper.insertItem(durationOfTrainings, durationOfTraining, name, durationOfTrainingMapper,
                durationOfTrainingService);
    }

    private boolean validateDurationOfTrainingInputs() {
        return Validation.checkData(tfNameDurationOfTraining) && Validation.checkData(cbModeOfStudyDoT,
                cbDurationOfStudyDoT);
    }

    //-------------------------------------------------------------------------------
    // ECTS CREDITS
    //-------------------------------------------------------------------------------
    private void initializeTableEctsCredits() {
        tblColIdEctsCredits.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColNameEctsCredits.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblColDurationOfStudyEctsCredits.setCellValueFactory(
                param -> param.getValue().durationOfStudyProperty());
        tblColDurationOfStudyEctsCredits.setCellFactory(ComboBoxTableCell.forTableColumn(durationOfStudies));
        tblColDurationOfStudyEctsCredits.setOnEditCommit(event -> {
            event.getRowValue().setDurationOfStudy(event.getNewValue());
            Helper.updateItem(event.getRowValue(), ectsCreditsMapper, ectsCreditsService);
        });

        tblColNameEctsCredits.setCellFactory(TextFieldTableCell.forTableColumn());
        tblColNameEctsCredits.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
            Helper.updateItem(event.getRowValue(), ectsCreditsMapper, ectsCreditsService);
        });

        tblEctsCredits.setRowFactory(
                tableView -> {
                    final TableRow<EctsCredits> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> Helper.removeItem(row.getItem().getId(), row.getItem(),
                            ectsCreditsService, tblEctsCredits));
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    private void addEctsCredits() {
        final String name = tfNameEctsCredits.getText().trim();
        final DurationOfStudy durationOfStudy = cbDurationOfStudyEctsCredits.getSelectionModel().getSelectedItem();
        final EctsCredits ectsCredits = new EctsCredits(this.ectsCredits.size() + 1, name, durationOfStudy);

        Helper.insertItem(this.ectsCredits, ectsCredits, name, ectsCreditsMapper, ectsCreditsService);
    }

    private boolean validateEctsCreditsInputs() {
        return Validation.checkData(tfNameEctsCredits) && Validation.checkData(cbDurationOfStudyEctsCredits);
    }

    //-------------------------------------------------------------------------------
    //  ACCESS REQUIREMENTS
    //-------------------------------------------------------------------------------
    private void initializeTableAccessRequirements() {
        tblColIdAccessRequirements.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColNameAccessRequirements.setCellValueFactory(new PropertyValueFactory<>("name"));

        tblColNameAccessRequirements.setCellFactory(TextFieldTableCell.forTableColumn());
        tblColNameAccessRequirements.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
            Helper.updateItem(event.getRowValue(), accessRequirementsMapper, accessRequirementsService);
        });

        tblAccessRequirements.setRowFactory(
                tableView -> {
                    final TableRow<AccessRequirements> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> Helper.removeItem(row.getItem().getId(), row.getItem(),
                            accessRequirementsService, tblAccessRequirements));
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    private void addAccessRequirements() {
        final String name = tfNameAccessRequirements.getText().trim();
        final AccessRequirements accessRequirements =
                new AccessRequirements(this.accessRequirements.size() + 1, name);

        Helper.insertItem(this.accessRequirements, accessRequirements, name, accessRequirementsMapper,
                accessRequirementsService);
    }

    private boolean validateAccessRequirementsInputs() {
        return Validation.checkData(tfNameAccessRequirements);
    }

    //-------------------------------------------------------------------------------
    //  EDUCATIONAL TEMPLATE
    //-------------------------------------------------------------------------------

    private void addEducationalTemplate() {
        final String courseTitle = tfNameEducationalTemplate.getText().trim();
        final double credits = Double.parseDouble(tfCreditsEducationalTemplate.getText().trim());
        final EducationalComponentType educationalComponentType =
                cbTypeEducationalTemplate.getSelectionModel().getSelectedItem();
        final EducationalComponentTemplate educationalComponentTemplate =
                new EducationalComponentTemplate(this.educationalComponentTemplates.size() + 1, credits, courseTitle,
                        educationalComponentType);
        Helper.insertItem(this.educationalComponentTemplates, educationalComponentTemplate, courseTitle,
                educationalComponentTemplateMapper, educationalComponentTemplateService);
    }

    private boolean validateEducationalTemplateInputs() {
        return Validation.checkData(tfNameEducationalTemplate, tfCreditsEducationalTemplate) &&
                Validation.checkData(cbTypeEducationalTemplate);
    }

    private void initializeTableEducationalTemplate() {
        tblColIdEducationalTemplate.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColNameEducationalTemplate.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        tblColCreditsEducationalTemplate.setCellValueFactory(new PropertyValueFactory<>("credits"));
        tblColTypeEducationalTemplate.setCellValueFactory(
                param -> param.getValue().educationalComponentTypeProperty());

        tblColTypeEducationalTemplate.setCellFactory(ComboBoxTableCell.forTableColumn(educationalComponentTypes));
        tblColTypeEducationalTemplate.setOnEditCommit(event -> {
            event.getRowValue().setEducationalComponentType(event.getNewValue());
            Helper.updateItem(event.getRowValue(), educationalComponentTemplateMapper,
                    educationalComponentTemplateService);
        });

        tblColNameEducationalTemplate.setCellFactory(TextFieldTableCell.forTableColumn());
        tblColNameEducationalTemplate.setOnEditCommit(event -> {
            event.getRowValue().setCourseTitle(event.getNewValue());
            Helper.updateItem(event.getRowValue(), educationalComponentTemplateMapper,
                    educationalComponentTemplateService);
        });

        tblColCreditsEducationalTemplate.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tblColCreditsEducationalTemplate.setOnEditCommit(event -> {
            event.getRowValue().setCredits(event.getNewValue());
            Helper.updateItem(event.getRowValue(), educationalComponentTemplateMapper,
                    educationalComponentTemplateService);
        });

        tblEducationalTemplate.setRowFactory(
                tableView -> {
                    final TableRow<EducationalComponentTemplate> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    final MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> {
                        int id = row.getItem().getId();
                        Helper.removeItem(id, row.getItem(),
                                educationalComponentTemplateService, tblEducationalTemplate);
                        try {
                            educationalComponentService.deleteByTemplateId(id);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                    rowMenu.getItems().addAll(removeItem);

                    // only display context menu for non-null items:
                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu) null));
                    return row;
                });
    }

    void display() throws Exception {
        final Parent root = SpringFXMLLoader.create()
                .applicationContext(Main.getContext())
                .location(FXMLStudentController.class
                        .getResource("/fxml/settings.fxml"))
                .charset(StandardCharsets.UTF_8)
                .load();

        final Scene scene = new Scene(root);

        stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Налаштування");

        //setting up min width & height parameters for window
        stage.setMinWidth(900);
        stage.setMinHeight(600);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @SuppressWarnings("unused")
    private void closeWindow() {
        stage.close();
    }

    void setTab(Tab tab) {
        this.tab = tab;
    }

    public enum Tab {
        PROTOCOLS, MAIN_FIELD, FIELD_OF_STUDY, GROUPS, OFFICIAL_DURATION,
        DURATION_OF_TRAINING, ECTS_CREDITS, ACCESS_REQUIREMENTS, EDUCATIONAL_TEMPLATE;
    }

    private static class Helper {
        private static <T1, T2> void fillTable(ObservableList<T1> list, Mapper<T2, T1> mapper,
                                               BaseServiceImpl<T2> service, TableView<T1> tableView) {
            try {
                list.addAll(mapper.map(service.getAll()));
                tableView.setItems(list);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося отримати дані про сервіси з БД", e);
            }
        }

        @SafeVarargs
        private static <T1, T2> void fillComboBoxes(ObservableList<T1> list, Mapper<T2, T1> mapper,
                                                    BaseServiceImpl<T2> service, ComboBox<T1>... comboBoxes) {
            try {
                list.addAll(mapper.map(service.getAll()));
                for (ComboBox<T1> comboBox :
                        comboBoxes) {
                    comboBox.getItems().addAll(list);
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося отримати дані про сервіси з БД", e);
            }
        }

        private static <T1, T2> void insertItem(ObservableList<T1> list, T1 item, String name, Mapper<T2, T1> mapper,
                                                BaseServiceImpl<T2> service) {
            try {
                if (service.create(mapper.reverseMap(item)) == 1) {
                    list.add(mapper.map(service.getByName(name)));
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося додати дані про сервіс у БД", e);
            }
        }

        private static <T1, T2> void updateItem(T1 item, Mapper<T2, T1> mapper,
                                                BaseServiceImpl<T2> service) {
            try {
                service.update(mapper.reverseMap(item));
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося оновити дані про сервіс", e);
            }
        }

        private static <T1, T2> void removeItem(int id, T1 item, BaseServiceImpl<T2> service, TableView<T1> tableView) {
            try {
                if (service.delete(id) == 1) {
                    tableView.getItems().remove(item);
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося видалити дані про сервіс", e);
            }
        }
    }
}
