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
    private TextArea taDurationOfTraining;

    @FXML
    private TextArea taInformationOnCertification;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private DatePicker dpDate;

    @FXML
    private ComboBox<MainField> cbMainField;

    @FXML
    private ComboBox<FieldOfStudy> cbFieldOfStudy;

    @FXML
    private ComboBox<AccessRequirements> cbAccessRequirements;

    @FXML
    private ComboBox<ModeOfStudy> cbModeOfStudy;

    @FXML
    private ComboBox<Protocol> cbProtocol;

    @FXML
    private ComboBox<DurationOfStudy> cbDurationOfStudy;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private Stage stage;

    private Integer studentId;

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
    private EducationalComponentTypeService educationalComponentTypeService;
    private EducationalComponentTemplateService educationalComponentTemplateService;
    private EducationalComponentService educationalComponentService;
    private DurationOfStudyService durationOfStudyService;

    private ProtocolMapper protocolMapper;
    private PreviousDocumentMapper previousDocumentMapper;
    private MainFieldMapper mainFieldMapper;
    private FieldOfStudyMapper fieldOfStudyMapper;
    private OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper;
    private AccessRequirementsMapper accessRequirementsMapper;
    private ModeOfStudyMapper modeOfStudyMapper;
    private DurationOfTrainingMapper durationOfTrainingMapper;
    private EducationalComponentTypeMapper educationalComponentTypeMapper;
    private EducationalComponentTemplateMapper educationalComponentTemplateMapper;
    private EducationalComponentMapper educationalComponentMapper;
    private DurationOfStudyMapper durationOfStudyMapper;

    private ObservableList<Protocol> protocolObservableList = FXCollections.observableArrayList();
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
    private ObservableList<DurationOfStudy> durationOfStudyObservableList = FXCollections
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
                                 EducationalComponentTypeService educationalComponentTypeService,
                                 EducationalComponentTemplateService educationalComponentTemplateService,
                                 EducationalComponentService educationalComponentService,
                                 DurationOfStudyService durationOfStudyService,
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
                                 EducationalComponentMapper educationalComponentMapper,
                                 DurationOfStudyMapper durationOfStudyMapper) {
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
        this.educationalComponentTypeService = educationalComponentTypeService;
        this.educationalComponentTemplateService = educationalComponentTemplateService;
        this.educationalComponentService = educationalComponentService;
        this.durationOfStudyService = durationOfStudyService;

        this.protocolMapper = protocolMapper;
        this.previousDocumentMapper = previousDocumentMapper;
        this.mainFieldMapper = mainFieldMapper;
        this.fieldOfStudyMapper = fieldOfStudyMapper;
        this.officialDurationOfProgrammeMapper = officialDurationOfProgrammeMapper;
        this.accessRequirementsMapper = accessRequirementsMapper;
        this.modeOfStudyMapper = modeOfStudyMapper;
        this.durationOfTrainingMapper = durationOfTrainingMapper;
        this.educationalComponentTypeMapper = educationalComponentTypeMapper;
        this.educationalComponentTemplateMapper = educationalComponentTemplateMapper;
        this.educationalComponentMapper = educationalComponentMapper;
        this.durationOfStudyMapper = durationOfStudyMapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearObservableLists();
        initializeObservableLists();
        initializeComboBoxes();
        initializeTableView();
        setListenersOnButtons();
        setListenersOnInputs();
    }

    private void initializeComboBoxes() {
        cbModeOfStudy.getItems().addAll(modeOfStudyObservableList);
        cbDurationOfStudy.getItems().addAll(durationOfStudyObservableList);
        cbMainField.getItems().addAll(mainFieldObservableList);
        cbFieldOfStudy.getItems().addAll(fieldOfStudyObservableList);
        cbProtocol.getItems().addAll(protocolObservableList);
        cbAccessRequirements.getItems().addAll(accessRequirementsObservableList);
    }

    private void initializeTableView() {

        for (EducationalComponentTemplate educationalComponentTemplate :
                educationalComponentTemplateObservableList) {
            educationalComponentObservableList.add(new EducationalComponent(educationalComponentTemplate.getId(),
                    0, educationalComponentTemplate.getCredits(),
                    educationalComponentTemplate.getCourseTitle(), educationalComponentTemplate
                    .getEducationalComponentType().getName(), educationalComponentTemplate,
                    null, null, null));
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
    
    private void clearObservableLists() {
        modeOfStudyObservableList.clear();
        durationOfStudyObservableList.clear();
        mainFieldObservableList.clear();
        fieldOfStudyObservableList.clear();
        protocolObservableList.clear();
        accessRequirementsObservableList.clear();
        educationalComponentObservableList.clear();
        educationalComponentTemplateObservableList.clear();
        educationalComponentTypeObservableList.clear();
    }

    private void initializeObservableLists() {
        try {
            modeOfStudyObservableList.addAll(modeOfStudyMapper.map(modeOfStudyService.getAll()));
            durationOfStudyObservableList.addAll(durationOfStudyMapper.map(durationOfStudyService.getAll()));
            mainFieldObservableList.addAll(mainFieldMapper.map(mainFieldService.getAll()));
            fieldOfStudyObservableList.addAll(fieldOfStudyMapper.map(fieldOfStudyService.getAll()));
            protocolObservableList.addAll(protocolMapper.map(protocolService.getAll()));
            accessRequirementsObservableList.addAll(accessRequirementsMapper.map(accessRequirementsService.getAll()));
            educationalComponentTypeObservableList.addAll(educationalComponentTypeMapper
                    .map(educationalComponentTypeService.getAll()));
            educationalComponentTemplateObservableList.addAll(educationalComponentTemplateMapper
                    .map(educationalComponentTemplateService.getAll()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeEditStudentWindow() {

    }

    private void setListenersOnButtons() {
        btnCancel.setOnMouseClicked(e -> closeWindow());
        btnSave.setOnMouseClicked(e -> {
            for (EducationalComponent educationalComponent :
                    educationalComponentObservableList) {
                System.out.println(educationalComponent);
            }
//            if (validateInputs()) {
//                addStudent();
//                closeWindow();
//            }
        });
    }

    private void setListenersOnInputs() {

        // listeners for Duration of Programme & Official Duration of Programme
        cbModeOfStudy.valueProperty().addListener(observable -> setDurationOfTraining(cbModeOfStudy.getSelectionModel()
                        .getSelectedItem(), cbDurationOfStudy.getSelectionModel().getSelectedItem()));

        cbDurationOfStudy.valueProperty().addListener(observable -> setDurationOfTraining(cbModeOfStudy.
                        getSelectionModel().getSelectedItem(), cbDurationOfStudy.getSelectionModel().getSelectedItem()));

        // listeners for Information on Certification
        tfDiplomaSubjectUk.textProperty().addListener(observable -> {
            if (tfDiplomaSubjectEn.getText().trim().length() != 0 && cbProtocol.getSelectionModel()
                    .getSelectedItem() != null) {
                setInformationOnCertification();
            }
        });

        tfDiplomaSubjectEn.textProperty().addListener(observable -> {
            if (tfDiplomaSubjectUk.getText().trim().length() != 0 && cbProtocol.getSelectionModel()
                    .getSelectedItem() != null) {
                setInformationOnCertification();
            }
        });

        cbProtocol.valueProperty().addListener(observable -> {
            if (tfDiplomaSubjectUk.getText().trim().length() != 0 && tfDiplomaSubjectEn.getText().trim().length() != 0) {
                setInformationOnCertification();
            }
        });
    }

    private void setDurationOfTraining(ModeOfStudy modeOfStudy, DurationOfStudy durationOfStudy) {
        if (modeOfStudy != null && durationOfStudy != null) {
            try {
                taDurationOfTraining.setText(durationOfTrainingMapper.map(durationOfTrainingService
                        .getByModeAndDurationOfStudy(modeOfStudy.getId(), durationOfStudy.getId())).getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setInformationOnCertification() {
        taInformationOnCertification.setText("Бакалаврська робота - \"" +
                tfDiplomaSubjectUk.getText().trim() + "\" " + cbProtocol.getSelectionModel()
                .getSelectedItem().getNameUK() + " / " + "Bachelor's Thesis - \"" +
                tfDiplomaSubjectEn.getText().trim() + "\" " + cbProtocol.getSelectionModel()
                .getSelectedItem().getNameEN());

        taInformationOnCertification.setWrapText(true);
    }

    private void addStudent() {

        final Student student = new Student();
        final Diploma diploma = new Diploma();

        LocalDate lc = dpDateOfBirth.getValue();
        Calendar c = Calendar.getInstance();
        c.set(lc.getYear(), lc.getMonthValue(), lc.getDayOfMonth());

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

    public void setStudentId(Integer id) {
        this.studentId = id;
    }
}
