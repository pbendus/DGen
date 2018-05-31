package ui.controllers;

import db.mapper.*;
import db.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.*;
import ui.utils.SpringFXMLLoader;
import ui.utils.Validation;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Controller("fxmlStudentController")
public class FXMLStudentController implements Initializable {

    @FXML
    private AnchorPane ap;

    @FXML
    private TitledPane tpGeneralInfo;

    @FXML
    private TitledPane tpDiplomaInfo;

    @FXML
    private TitledPane tpEducationResults;

    @FXML
    private TableView<EducationalComponent> tvGrades;

    @FXML
    private TableColumn<EducationalComponent, Integer> tcNumber;

    @FXML
    private TableColumn<EducationalComponent, String> tcType;

    @FXML
    private TableColumn<EducationalComponent, String> tcName;

    @FXML
    private TableColumn<EducationalComponent, Double> tcCredit;

    @FXML
    private TableColumn<EducationalComponent, Integer> tcGrade;

    @FXML
    private TextField tfFamilyName;

    @FXML
    private TextField tfFamilyNameTr;

    @FXML
    private TextField tfGivenName;

    @FXML
    private TextField tfGivenNameTr;

    @FXML
    private TextField tfDiplomaSubjectUk;

    @FXML
    private TextField tfDiplomaSubjectEn;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfRegistrationNumber;

    @FXML
    private TextArea taInformationOnCertification;

    @FXML
    private TextArea taProfessionalStatus;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private DatePicker dpDate;

    @FXML
    private ComboBox<PreviousDocument> cbPreviousDocument;

    @FXML
    private ComboBox<MainField> cbMainField;

    @FXML
    private ComboBox<FieldOfStudy> cbFieldOfStudy;

    @FXML
    private ComboBox<OfficialDurationOfProgramme> cbOfficialDuration;

    @FXML
    private ComboBox<AccessRequirements> cbAccessRequirements;

    @FXML
    private ComboBox<ModeOfStudy> cbModeOfStudy;

    @FXML
    private ComboBox<Protocol> cbProtocol;

    @FXML
    private ComboBox<DurationOfTraining> cbDurationOfTraining;

    @FXML
    private ComboBox<ClassificationSystem> cbClassificationSystem;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private Stage stage;

    private StudentService studentService;
    private ProtocolService protocolService;
    private PreviousDocumentService previousDocumentService;
    private MainFieldService mainFieldService;
    private FieldOfStudyService fieldOfStudyService;
    private OfficialDurationOfProgrammeService officialDurationOfProgrammeService;
    private AccessRequirementsService accessRequirementsService;
    private ModeOfStudyService modeOfStudyService;
    private DurationOfTrainingService durationOfTrainingService;
    private DiplomaSubjectService diplomaSubjectService;
    private DiplomaService diplomaService;
    private EducationalComponentTypeService educationalComponentTypeService;
    private EducationalComponentTemplateService educationalComponentTemplateService;
    private EducationalComponentService educationalComponentService;

    private ProtocolMapper protocolMapper;
    private PreviousDocumentMapper previousDocumentMapper;
    private MainFieldMapper mainFieldMapper;
    private FieldOfStudyMapper fieldOfStudyMapper;
    private OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper;
    private AccessRequirementsMapper accessRequirementsMapper;
    private ModeOfStudyMapper modeOfStudyMapper;
    private DurationOfTrainingMapper durationOfTrainingMapper;
    private ClassificationSystemMapper classificationSystemMapper;
    private EducationalComponentTypeMapper educationalComponentTypeMapper;
    private EducationalComponentTemplateMapper educationalComponentTemplateMapper;
    private EducationalComponentMapper educationalComponentMapper;

    private ObservableList<Protocol> protocolObservableListList = FXCollections.observableArrayList();
    private ObservableList<PreviousDocument> previousDocumentObservableList = FXCollections.observableArrayList();
    private ObservableList<MainField> mainFieldObservableList = FXCollections.observableArrayList();
    private ObservableList<FieldOfStudy> fieldOfStudyObservableList = FXCollections.observableArrayList();
    private ObservableList<OfficialDurationOfProgramme> officialDurationOfProgrammeObservableList = FXCollections
            .observableArrayList();
    private ObservableList<AccessRequirements> accessRequirementsObservableList = FXCollections.observableArrayList();
    private ObservableList<ModeOfStudy> modeOfStudyObservableList = FXCollections.observableArrayList();
    private ObservableList<DurationOfTraining> durationOfTrainingObservableList = FXCollections.observableArrayList();
    private ObservableList<EducationalComponentType> educationalComponentTypeObservableList = FXCollections
            .observableArrayList();
    private ObservableList<EducationalComponentTemplate> educationalComponentTemplateObservableList = FXCollections
            .observableArrayList();
    private ObservableList<EducationalComponent> educationalComponentObservableList = FXCollections
            .observableArrayList();

    @Autowired
    public FXMLStudentController(StudentService studentService,
                                 ProtocolService protocolService,
                                 PreviousDocumentService previousDocumentService,
                                 MainFieldService mainFieldService,
                                 FieldOfStudyService fieldOfStudyService,
                                 OfficialDurationOfProgrammeService officialDurationOfProgrammeService,
                                 AccessRequirementsService accessRequirementsService,
                                 ModeOfStudyService modeOfStudyService,
                                 DurationOfTrainingService durationOfTrainingService,
                                 DiplomaSubjectService diplomaSubjectService,
                                 DiplomaService diplomaService,
                                 EducationalComponentTypeService educationalComponentTypeService,
                                 EducationalComponentTemplateService educationalComponentTemplateService,
                                 EducationalComponentService educationalComponentService,
                                 ProtocolMapper protocolMapper,
                                 PreviousDocumentMapper previousDocumentMapper,
                                 MainFieldMapper mainFieldMapper,
                                 FieldOfStudyMapper fieldOfStudyMapper,
                                 OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper,
                                 AccessRequirementsMapper accessRequirementsMapper,
                                 ModeOfStudyMapper modeOfStudyMapper,
                                 DurationOfTrainingMapper durationOfTrainingMapper,
                                 ClassificationSystemMapper classificationSystemMapper,
                                 EducationalComponentTypeMapper educationalComponentTypeMapper,
                                 EducationalComponentTemplateMapper educationalComponentTemplateMapper,
                                 EducationalComponentMapper educationalComponentMapper) {
        this.studentService = studentService;
        this.protocolService = protocolService;
        this.previousDocumentService = previousDocumentService;
        this.mainFieldService = mainFieldService;
        this.fieldOfStudyService = fieldOfStudyService;
        this.officialDurationOfProgrammeService = officialDurationOfProgrammeService;
        this.accessRequirementsService = accessRequirementsService;
        this.modeOfStudyService = modeOfStudyService;
        this.durationOfTrainingService = durationOfTrainingService;
        this.diplomaSubjectService = diplomaSubjectService;
        this.diplomaService = diplomaService;
        this.educationalComponentTypeService = educationalComponentTypeService;
        this.educationalComponentTemplateService = educationalComponentTemplateService;
        this.educationalComponentService = educationalComponentService;

        this.protocolMapper = protocolMapper;
        this.previousDocumentMapper = previousDocumentMapper;
        this.mainFieldMapper = mainFieldMapper;
        this.fieldOfStudyMapper = fieldOfStudyMapper;
        this.officialDurationOfProgrammeMapper = officialDurationOfProgrammeMapper;
        this.accessRequirementsMapper = accessRequirementsMapper;
        this.modeOfStudyMapper = modeOfStudyMapper;
        this.durationOfTrainingMapper = durationOfTrainingMapper;
        this.classificationSystemMapper = classificationSystemMapper;
        this.educationalComponentTypeMapper = educationalComponentTypeMapper;
        this.educationalComponentTemplateMapper = educationalComponentTemplateMapper;
        this.educationalComponentMapper = educationalComponentMapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComboBoxes();
        initializeTableView();
        setListenersOnButtons();
        setListenersOnInformationOnCertification();
    }

    private void setListenersOnButtons() {
        btnCancel.setOnMouseClicked(e -> closeWindow());
        btnSave.setOnMouseClicked(e -> {
            for(EducationalComponent educationalComponent:
                    educationalComponentObservableList) {
                System.out.println(educationalComponent);
            }
//            if (validateInputs()) {
//                addStudent();
//                closeWindow();
//            }
        });
    }

    private void initializeComboBoxes() {
        try {
            protocolObservableListList.addAll(protocolMapper.map(protocolService.getAll()));
            previousDocumentObservableList.addAll(previousDocumentMapper.map(previousDocumentService.getAll()));
            mainFieldObservableList.addAll(mainFieldMapper.map(mainFieldService.getAll()));
            fieldOfStudyObservableList.addAll(fieldOfStudyMapper.map(fieldOfStudyService.getAll()));
            officialDurationOfProgrammeObservableList.addAll(officialDurationOfProgrammeMapper
                    .map(officialDurationOfProgrammeService.getAll()));
            accessRequirementsObservableList.addAll(accessRequirementsMapper.map(accessRequirementsService.getAll()));
            modeOfStudyObservableList.addAll(modeOfStudyMapper.map(modeOfStudyService.getAll()));
            durationOfTrainingObservableList.addAll(durationOfTrainingMapper.map(durationOfTrainingService.getAll()));
            educationalComponentTypeObservableList.addAll(educationalComponentTypeMapper
                    .map(educationalComponentTypeService.getAll()));
            educationalComponentTemplateObservableList.addAll(educationalComponentTemplateMapper
                    .map(educationalComponentTemplateService.getAll()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        cbProtocol.getItems().addAll(protocolObservableListList);
        cbPreviousDocument.getItems().addAll(previousDocumentObservableList);
        cbMainField.getItems().addAll(mainFieldObservableList);
        cbFieldOfStudy.getItems().addAll(fieldOfStudyObservableList);
        cbOfficialDuration.getItems().addAll(officialDurationOfProgrammeObservableList);
        cbAccessRequirements.getItems().addAll(accessRequirementsObservableList);
        cbModeOfStudy.getItems().addAll(modeOfStudyObservableList);
        cbDurationOfTraining.getItems().addAll(durationOfTrainingObservableList);
    }

    private void initializeTableView() {
        try {
            educationalComponentObservableList.addAll(educationalComponentMapper
                    .map(educationalComponentService.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tcNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("educationalComponentType"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        tcCredit.setCellValueFactory(new PropertyValueFactory<>("credits"));
        tcGrade.setCellValueFactory(new PropertyValueFactory<>("nationalScore"));

        tvGrades.setItems(educationalComponentObservableList);

        tvGrades.setEditable(true);
        tcCredit.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tcGrade.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    private void addStudent() {

        List<db.entities.Student> studentList = new ArrayList<>();
        List<db.entities.DiplomaSubject> diplomaSubjectList = new ArrayList<>();

        try {
            studentList = studentService.getAll();
            diplomaSubjectList = diplomaSubjectService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalDate lc = dpDateOfBirth.getValue();
        Calendar c = Calendar.getInstance();
        c.set(lc.getYear(), lc.getMonthValue(), lc.getDayOfMonth());

        final int id = studentList.size() + 1;
        final String familyName = tfFamilyName.getText().trim();
        final String givenName = tfGivenName.getText().trim();
        final String familyNameTr = tfFamilyNameTr.getText().trim();
        final String givenNameTr = tfGivenNameTr.getText().trim();
        final Date dateOfBirth = c.getTime();
        final db.entities.Protocol protocol = protocolMapper.reverseMap(cbProtocol.getSelectionModel()
                .getSelectedItem());
        final db.entities.DiplomaSubject diplomaSubject = new db.entities.DiplomaSubject(diplomaSubjectList.size() + 1,
                tfDiplomaSubjectUk.getText().trim(), tfDiplomaSubjectEn.getText().trim());
        final db.entities.PreviousDocument previousDocument = previousDocumentMapper.reverseMap(cbPreviousDocument
                .getSelectionModel().getSelectedItem());

        db.entities.Student student = new db.entities.Student(id, familyName, givenName, familyNameTr, givenNameTr,
            dateOfBirth, protocol, diplomaSubject, previousDocument,
            modeOfStudyMapper.reverseMap(cbModeOfStudy.getSelectionModel().getSelectedItem()),
            null);

        LocalDate lc2 = dpDate.getValue();
        c.set(lc2.getYear(), lc2.getMonthValue(), lc2.getDayOfMonth());

        final String number = tfNumber.getText().trim();
        final String registrationNumber = tfRegistrationNumber.getText().trim();
        final Date dateOfIssue = c.getTime();
        final db.entities.MainField mainField = mainFieldMapper.reverseMap(cbMainField.getSelectionModel().getSelectedItem());
        final db.entities.FieldOfStudy fieldOfStudy = fieldOfStudyMapper.reverseMap(cbFieldOfStudy.getSelectionModel()
                .getSelectedItem());
        final db.entities.OfficialDurationOfProgramme officialDurationOfProgramme = officialDurationOfProgrammeMapper
                .reverseMap(cbOfficialDuration.getSelectionModel().getSelectedItem());
        final db.entities.AccessRequirements accessRequirements = accessRequirementsMapper
                .reverseMap(cbAccessRequirements.getSelectionModel().getSelectedItem());
        final db.entities.ModeOfStudy modeOfStudy = modeOfStudyMapper.reverseMap(cbModeOfStudy.getSelectionModel()
                .getSelectedItem());
        final db.entities.ClassificationSystem classificationSystem = classificationSystemMapper
                .reverseMap(cbClassificationSystem.getSelectionModel().getSelectedItem());
        final db.entities.DurationOfTraining durationOfTraining = durationOfTrainingMapper
                .reverseMap(cbDurationOfTraining.getSelectionModel().getSelectedItem());

        db.entities.Diploma diploma = new db.entities.Diploma(id, number, registrationNumber,
            null, dateOfIssue, student,
                mainField, fieldOfStudy, officialDurationOfProgramme, accessRequirements,
            classificationSystem, durationOfTraining, null);

        try {
            studentService.create(student);
            diplomaService.create(diploma);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setListenersOnInformationOnCertification() {
        tfDiplomaSubjectUk.textProperty().addListener(observable -> {
            if (tfDiplomaSubjectEn.getText().trim().length() != 0 && cbProtocol.getSelectionModel()
                    .getSelectedItem() != null) {
                displayInformationOnCertification();
            }
        });

        tfDiplomaSubjectEn.textProperty().addListener(observable -> {
            if (tfDiplomaSubjectUk.getText().trim().length() != 0 && cbProtocol.getSelectionModel()
                    .getSelectedItem() != null) {
                displayInformationOnCertification();
            }
        });

        cbProtocol.valueProperty().addListener(observable -> {
            if (tfDiplomaSubjectUk.getText().trim().length() != 0 && tfDiplomaSubjectEn.getText().trim().length() != 0) {
                displayInformationOnCertification();
            }
        });
    }

    private void displayInformationOnCertification() {
        taInformationOnCertification.setText("Бакалаврська робота - \"" +
                tfDiplomaSubjectUk.getText().trim() + "\" " + cbProtocol.getSelectionModel()
                .getSelectedItem().getNameUK() + " / " + "Bachelor's Thesis - \"" +
                tfDiplomaSubjectEn.getText().trim() + "\" " + cbProtocol.getSelectionModel()
                .getSelectedItem().getNameEN());

        taInformationOnCertification.setWrapText(true);
    }

    private boolean validateInputs() {
        boolean result = true;

        if (!Validation.validateTextField(tfFamilyName)) {
            tfFamilyName.setStyle(Validation.getTextFieldErrorStyle());
            result = false;
        }

        if (!Validation.validateTextField(tfFamilyName)) {
            tfFamilyName.setStyle(Validation.getTextFieldErrorStyle());
            result = false;
        }

        if (!Validation.validateTextField(tfFamilyName)) {
            tfFamilyName.setStyle(Validation.getTextFieldErrorStyle());
            result = false;
        }

        return result;
    }

    private void closeWindow() {
        stage.close();
    }

    void display() throws Exception {
        Parent root = SpringFXMLLoader.create()
                .applicationContext(Main.getContext())
                .location(FXMLStudentController.class
                        .getResource("../../fxml/student.fxml"))
                .load();

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Додати студента");

        //setting up min width & height parameters for window
        stage.setMinWidth(600);
        stage.setMinHeight(450);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
