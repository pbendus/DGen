package ui.controllers;

import db.entities.ClassificationSystemConst;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

@Controller("fxmlStudentController")
public class FXMLStudentController implements Initializable {

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane tpGeneralInfo;

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
    private TextField tfPreviousDocument;

    @FXML
    private TextField tfPreviousDocumentEn;

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
    private ComboBox<Group> cbGroup;

    @FXML
    private CheckBox chkboxClassificationSystem;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private Stage stage;

    private int studentId;

    private static final Logger LOGGER = LogManager.getLogger();

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
    private ArDosService arDosService;
    private GroupService groupService;
    private DiplomaService diplomaService;
    private EctsCreditsService ectsCreditsService;
    private ClassificationSystemService classificationSystemService;

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
    private GroupMapper groupMapper;
    private EctsCreditsMapper ectsCreditsMapper;
    private ClassificationSystemMapper classificationSystemMapper;
    private StudentMapper studentMapper;
    private DiplomaMapper diplomaMapper;
    private DiplomaSubjectMapper diplomaSubjectMapper;

    private ObservableList<Protocol> protocolObservableList = FXCollections.observableArrayList();
    private ObservableList<MainField> mainFieldObservableList = FXCollections.observableArrayList();
    private ObservableList<FieldOfStudy> fieldOfStudyObservableList = FXCollections.observableArrayList();
    private ObservableList<AccessRequirements> accessRequirementsObservableList = FXCollections.observableArrayList();
    private ObservableList<ModeOfStudy> modeOfStudyObservableList = FXCollections.observableArrayList();
    private ObservableList<EducationalComponentType> educationalComponentTypeObservableList = FXCollections
            .observableArrayList();
    private ObservableList<EducationalComponentTemplate> educationalComponentTemplateObservableList = FXCollections
            .observableArrayList();
    private ObservableList<EducationalComponent> educationalComponentObservableList = FXCollections
            .observableArrayList();
    private ObservableList<DurationOfStudy> durationOfStudyObservableList = FXCollections
            .observableArrayList();
    private ObservableList<Group> groupObservableList = FXCollections.observableArrayList();

    private StudentCallback studentCallback;

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
                                 ArDosService arDosService,
                                 GroupService groupService,
                                 DiplomaService diplomaService,
                                 EctsCreditsService ectsCreditsService,
                                 ClassificationSystemService classificationSystemService,
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
                                 DurationOfStudyMapper durationOfStudyMapper,
                                 GroupMapper groupMapper,
                                 EctsCreditsMapper ectsCreditsMapper,
                                 StudentMapper studentMapper,
                                 DiplomaMapper diplomaMapper,
                                 DiplomaSubjectMapper diplomaSubjectMapper) {
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
        this.arDosService = arDosService;
        this.groupService = groupService;
        this.ectsCreditsService = ectsCreditsService;
        this.classificationSystemService = classificationSystemService;
        this.diplomaService = diplomaService;

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
        this.ectsCreditsMapper = ectsCreditsMapper;
        this.classificationSystemMapper = classificationSystemMapper;
        this.studentMapper = studentMapper;
        this.diplomaMapper = diplomaMapper;
        this.groupMapper = groupMapper;
        this.diplomaSubjectMapper = diplomaSubjectMapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accordion.setExpandedPane(tpGeneralInfo);
        clearObservableLists();
        initializeObservableLists();
        initializeComboBoxes();
        setListenersOnButtons();
        setListenersOnInputs();

        if (studentId != 0) {
            setStudentInformation();
        } else {
            initializeTemplateTableView();
        }
    }

    private void initializeComboBoxes() {
        cbModeOfStudy.getItems().addAll(modeOfStudyObservableList);
        cbDurationOfStudy.getItems().addAll(durationOfStudyObservableList);
        cbMainField.getItems().addAll(mainFieldObservableList);
        cbFieldOfStudy.getItems().addAll(fieldOfStudyObservableList);
        cbProtocol.getItems().addAll(protocolObservableList);
        cbAccessRequirements.getItems().addAll(accessRequirementsObservableList);
        cbGroup.getItems().addAll(groupObservableList);
    }

    private void setStudentInformation() {
        Student student = null;
        Diploma diploma = null;

        try {
            student = studentMapper.map(studentService.getById(studentId));
            diploma = diplomaMapper.map(diplomaService.getByStudentId(studentId));
            educationalComponentObservableList.addAll(educationalComponentMapper.map(educationalComponentService
                    .getAllByDiplomaId(diploma.getId())));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Choosing template exception",
                    "Could not find file path", e);
        }

        LocalDate localDateOfBirth = student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate = diploma.getDateOfIssue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // set text fields
        tfFamilyName.setText(student.getFamilyName());
        tfGivenName.setText(student.getGivenName());
        tfFamilyNameTr.setText(student.getFamilyNameTr());
        tfGivenNameTr.setText(student.getGivenNameTr());
        tfPreviousDocument.setText(student.getPreviousDocument().getName());
        tfPreviousDocumentEn.setText(student.getPreviousDocument().getNameEN());
        tfDiplomaSubjectUk.setText(diploma.getDiplomaSubject().getSubjectUK());
        tfDiplomaSubjectEn.setText(diploma.getDiplomaSubject().getSubjectEN());
        tfNumber.setText(diploma.getNumber());
        tfRegistrationNumber.setText(diploma.getRegistrationNumber());
        taDurationOfTraining.setText(diploma.getDurationOfTraining().getName());

        // set date pickers
        dpDateOfBirth.setValue(localDateOfBirth);
        dpDate.setValue(localDate);

        // set comboboxes
        cbModeOfStudy.getSelectionModel().select(student.getModeOfStudy());
        cbDurationOfStudy.getSelectionModel().select(student.getDurationOfStudy());
        cbMainField.getSelectionModel().select(diploma.getMainField());
        cbFieldOfStudy.getSelectionModel().select(diploma.getFieldOfStudy());
        cbGroup.getSelectionModel().select(student.getGroup());
        cbProtocol.getSelectionModel().select(student.getProtocol());
        cbAccessRequirements.getSelectionModel().select(diploma.getAccessRequirements());

        // set checkbox
        if (diploma.getClassificationSystem().getName().equals(ClassificationSystemConst.DIPLOMA_WITH_HONORS)) {
            chkboxClassificationSystem.setSelected(true);
        }

        tcNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("educationalComponentType"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        tcCredit.setCellValueFactory(new PropertyValueFactory<>("credits"));
        tcGrade.setCellValueFactory(new PropertyValueFactory<>("nationalScore"));

        tcNumber.prefWidthProperty().bind(tvGrades.widthProperty().divide(5));
        tcType.prefWidthProperty().bind(tvGrades.widthProperty().divide(2));
        tcName.prefWidthProperty().bind(tvGrades.widthProperty().divide(2));
        tcCredit.prefWidthProperty().bind(tvGrades.widthProperty().divide(12));
        tcGrade.prefWidthProperty().bind(tvGrades.widthProperty().divide(12));

        tvGrades.setItems(educationalComponentObservableList);

        tvGrades.setEditable(true);
        tcCredit.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tcGrade.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    private void initializeTemplateTableView() {

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

        tcNumber.prefWidthProperty().bind(tvGrades.widthProperty().divide(5));
        tcType.prefWidthProperty().bind(tvGrades.widthProperty().divide(2));
        tcName.prefWidthProperty().bind(tvGrades.widthProperty().divide(2));
        tcCredit.prefWidthProperty().bind(tvGrades.widthProperty().divide(12));
        tcGrade.prefWidthProperty().bind(tvGrades.widthProperty().divide(12));

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
        groupObservableList.clear();
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
            groupObservableList.addAll(groupMapper.map(groupService.getAll()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Initializing observable lists exception",
                    "Could not get specific observable list from database", e);
        }
    }

    private void setListenersOnButtons() {
        btnCancel.setOnMouseClicked(e -> closeWindow());
        btnSave.setOnMouseClicked(e -> {
            if (validateInputs()) {
                if (studentId == 0) {
                    try {
                        addStudent();
                        AlertBox.showInformationDialog("Операцію виконано успішно",
                                "Інформацію про студента було успішно додано в БД");
                    } catch (SQLException e1) {
                        LOGGER.error(e1.getMessage());
                        AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                                "Не вдалося додати студента в БД", e1);
                    }
                } else {
                    try {
                        editStudent();
                        AlertBox.showInformationDialog("Операцію виконано успішно editing",
                                "Інформація про студента була успішно оновлена");
                    } catch (SQLException e1) {
                        LOGGER.error(e1.getMessage());
                        AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                                "Не вдалося оновити інформацію про студента", e1);
                    }
                }
                closeWindow();
            } else {
                AlertBox.showErrorDialog("Помилка валідації",
                        "Введіть усі необхідні дані про студента");
            }
        });
    }

    private void setListenersOnInputs() {

        // listeners for Duration of Programme & Official Duration of Programme
        cbModeOfStudy.valueProperty().addListener(observable -> setDurationOfTraining(cbModeOfStudy.getSelectionModel()
                .getSelectedItem(), cbDurationOfStudy.getSelectionModel().getSelectedItem()));

        cbDurationOfStudy.valueProperty().addListener(observable -> {
            setDurationOfTraining(cbModeOfStudy.
                    getSelectionModel().getSelectedItem(), cbDurationOfStudy.getSelectionModel().getSelectedItem());

            try {
                AccessRequirements accessRequirements = accessRequirementsMapper.map(arDosService
                        .getAccessRequirementsByDurationOfStudyId(cbDurationOfStudy.getSelectionModel()
                                .getSelectedItem().getId()));
                for (AccessRequirements accessRequirements1 :
                        accessRequirementsObservableList) {
                    if (accessRequirements.getId() == accessRequirements1.getId()) {
                        cbAccessRequirements.getSelectionModel().select(accessRequirements1);
                    }
                }

            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося отримати дані про вимоги до вступу з БД", e);
            }
        });

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
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося отримати дані про тривалість навчання з БД", e);
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

    private void addStudent() throws SQLException {

        Calendar c = Calendar.getInstance();
        // add student
        if (dpDateOfBirth != null) {
            LocalDate lc = dpDateOfBirth.getValue();
            c.set(lc.getYear(), lc.getMonthValue() - 1, lc.getDayOfMonth());
        }
        final int studentId = studentService.getAll().size() + 1;
        final String familyName = tfFamilyName.getText().trim();
        final String givenName = tfGivenName.getText().trim();
        final String familyNameTr = tfFamilyNameTr.getText().trim();
        final String givenNameTr = tfGivenNameTr.getText().trim();
        final Date dateOfBirth = c.getTime();
        final Protocol protocol = cbProtocol.getSelectionModel().getSelectedItem();
        final PreviousDocument previousDocument = new PreviousDocument(previousDocumentService.getAll().size() + 1,
                tfPreviousDocument.getText().trim(), tfPreviousDocumentEn.getText().trim());
        final ModeOfStudy modeOfStudy = cbModeOfStudy.getSelectionModel().getSelectedItem();
        final DurationOfStudy durationOfStudy = cbDurationOfStudy.getSelectionModel().getSelectedItem();
        final Group group = cbGroup.getSelectionModel().getSelectedItem();

        previousDocumentService.create(previousDocumentMapper.reverseMap(previousDocument));

        Student student = new Student(studentId, familyName, givenName, familyNameTr, givenNameTr, dateOfBirth,
                protocol, previousDocumentMapper.map(previousDocumentService.getByName(previousDocument.getName())),
                modeOfStudy, durationOfStudy, group);

        studentService.create(studentMapper.reverseMap(student));

        // add diploma
        if (dpDate != null) {
            LocalDate locDate = dpDate.getValue();
            c.set(locDate.getYear(), locDate.getMonthValue() - 1, locDate.getDayOfMonth());
        }
        final int diplomaId = diplomaService.getAll().size() + 1;
        final String number = tfNumber.getText().trim();
        final String registrationNumber = tfRegistrationNumber.getText().trim();
        final String additionRegistrationNumber = tfRegistrationNumber.getText().trim();
        final Date dateOfIssue = c.getTime();
        final MainField mainField = cbMainField.getSelectionModel().getSelectedItem();
        final FieldOfStudy fieldOfStudy = cbFieldOfStudy.getSelectionModel().getSelectedItem();
        final OfficialDurationOfProgramme officialDurationOfProgramme = officialDurationOfProgrammeMapper
                .map(officialDurationOfProgrammeService.getByModeAndDurationOfStudy(cbModeOfStudy.getSelectionModel()
                        .getSelectedItem().getId(), cbDurationOfStudy.getSelectionModel().getSelectedItem()
                        .getId()));
        final AccessRequirements accessRequirements = cbAccessRequirements.getSelectionModel().getSelectedItem();
        final EctsCredits ectsCredits = ectsCreditsMapper.map(ectsCreditsService.getByDurationOfStudy(cbDurationOfStudy
                .getSelectionModel().getSelectedItem().getId()));
        final ClassificationSystem classificationSystem = getClassificationSystem();
        final DurationOfTraining durationOfTraining = durationOfTrainingMapper.map(durationOfTrainingService
                .getByModeAndDurationOfStudy(modeOfStudy.getId(), durationOfStudy.getId()));
        final DiplomaSubject diplomaSubject = new DiplomaSubject(diplomaSubjectService.getAll().size() + 1,
                tfDiplomaSubjectUk.getText().trim(), tfDiplomaSubjectEn.getText().trim());

        diplomaSubjectService.create(diplomaSubjectMapper.reverseMap(diplomaSubject));

        final Diploma diploma = new Diploma(diplomaId, number, registrationNumber, additionRegistrationNumber,
                dateOfIssue, studentMapper.map(studentService.getByFullName(student.getFamilyName(),
                student.getGivenName())), mainField, fieldOfStudy, officialDurationOfProgramme, accessRequirements,
                ectsCredits, classificationSystem, durationOfTraining, diplomaSubjectMapper.map(diplomaSubjectService
                .getByName(diplomaSubject.getSubjectUK(), diplomaSubject.getSubjectEN())));

        diplomaService.create(diplomaMapper.reverseMap(diploma));


        // add educational component
        for (int i = 0; i < educationalComponentObservableList.size(); i++) {
            EducationalComponent educationalComponent = educationalComponentObservableList.get(i);
            educationalComponent.setDiploma(diplomaMapper.map(diplomaService
                    .getByNumber(diploma.getNumber())));
            educationalComponentService.create(educationalComponentMapper.reverseMap(educationalComponent));
        }

        studentCallback.addStudent(student);
    }

    private void editStudent() throws SQLException {
        // edit student
        Student student = studentMapper.map(studentService.getById(studentId));

        LocalDate lc = dpDateOfBirth.getValue();
        Calendar c = Calendar.getInstance();
        c.set(lc.getYear(), lc.getMonthValue() - 1, lc.getDayOfMonth());
        student.setFamilyName(tfFamilyName.getText().trim());
        student.setGivenName(tfGivenName.getText().trim());
        student.setFamilyNameTr(tfFamilyNameTr.getText().trim());
        student.setGivenNameTr(tfGivenNameTr.getText().trim());
        student.setDateOfBirth(c.getTime());
        student.setProtocol(cbProtocol.getSelectionModel().getSelectedItem());
        student.getPreviousDocument().setName(tfPreviousDocument.getText().trim());
        student.getPreviousDocument().setNameEN(tfPreviousDocumentEn.getText().trim());
        previousDocumentService.update(previousDocumentMapper.reverseMap(student.getPreviousDocument()));
        student.setModeOfStudyObject(cbModeOfStudy.getSelectionModel().getSelectedItem());
        student.setDurationOfStudy(cbDurationOfStudy.getSelectionModel().getSelectedItem());
        student.setGroup(cbGroup.getSelectionModel().getSelectedItem());

        studentService.update(studentMapper.reverseMap(student));

        // edit diploma
        Diploma diploma = diplomaMapper.map(diplomaService.getByStudentId(studentId));

        LocalDate locDate = dpDate.getValue();
        Calendar cal = Calendar.getInstance();
        cal.set(locDate.getYear(), locDate.getMonthValue() - 1, locDate.getDayOfMonth());
        diploma.setNumber(tfNumber.getText().trim());
        diploma.setRegistrationNumber(tfRegistrationNumber.getText().trim());
        diploma.setAdditionRegistrationNumber(tfRegistrationNumber.getText().trim());
        diploma.setDateOfIssue(cal.getTime());
        diploma.setMainField(cbMainField.getSelectionModel().getSelectedItem());
        diploma.setFieldOfStudy(cbFieldOfStudy.getSelectionModel().getSelectedItem());
        diploma.setOfficialDurationOfProgramme(officialDurationOfProgrammeMapper.map(officialDurationOfProgrammeService
                .getByModeAndDurationOfStudy(cbModeOfStudy.getSelectionModel().getSelectedItem().getId(),
                        cbDurationOfStudy.getSelectionModel().getSelectedItem().getId())));
        diploma.setAccessRequirements(cbAccessRequirements.getSelectionModel().getSelectedItem());
        diploma.setEctsCredits(ectsCreditsMapper.map(ectsCreditsService.getByDurationOfStudy(cbDurationOfStudy
                .getSelectionModel().getSelectedItem().getId())));
        diploma.setClassificationSystem(getClassificationSystem());
        diploma.setDurationOfTraining(durationOfTrainingMapper.map(durationOfTrainingService
                .getByModeAndDurationOfStudy(cbModeOfStudy.getSelectionModel().getSelectedItem().getId(),
                        cbDurationOfStudy.getSelectionModel().getSelectedItem().getId())));
        diploma.getDiplomaSubject().setSubjectUK(tfDiplomaSubjectUk.getText().trim());
        diploma.getDiplomaSubject().setSubjectUK(tfDiplomaSubjectEn.getText().trim());
        diplomaSubjectService.update(diplomaSubjectMapper.reverseMap(diploma.getDiplomaSubject()));

        diplomaService.update(diplomaMapper.reverseMap(diploma));

        // edit educational component
        for (int i = 0; i < educationalComponentObservableList.size(); i++) {
            EducationalComponent educationalComponent = educationalComponentObservableList.get(i);
            educationalComponentService.update(educationalComponentMapper.reverseMap(educationalComponent));
        }

        studentCallback.updateStudent(student);
    }

    private ClassificationSystem getClassificationSystem() {
        try {
            return chkboxClassificationSystem.isSelected() ? classificationSystemMapper.map(classificationSystemService
                    .getByName(ClassificationSystemConst.DIPLOMA_WITH_HONORS)) :
                    classificationSystemMapper.map(classificationSystemService.getByName(ClassificationSystemConst.DIPLOMA));
        } catch (SQLException e) {
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося отримати інформацію про присвоєну класифікацію", e);
        }

        return null;
    }

    private boolean validateInputs() {
        return Validation.checkData(tfFamilyName, tfFamilyNameTr, tfGivenName, tfGivenNameTr, tfPreviousDocument,
                tfPreviousDocumentEn, tfDiplomaSubjectUk, tfDiplomaSubjectEn, tfNumber, tfRegistrationNumber) &&
                Validation.checkData(cbModeOfStudy, cbDurationOfStudy, cbMainField, cbFieldOfStudy, cbProtocol,
                        cbAccessRequirements, cbGroup) &&
                Validation.checkData(dpDateOfBirth, dpDate);
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
        stage.setMinWidth(900);
        stage.setMinHeight(600);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    void setStudentId(int id) {
        this.studentId = id;
    }

    void setStudentCallback(StudentCallback studentCallback) {
        this.studentCallback = studentCallback;
    }

    public interface StudentCallback {
        void addStudent(Student student);

        void updateStudent(Student student);
    }
}
