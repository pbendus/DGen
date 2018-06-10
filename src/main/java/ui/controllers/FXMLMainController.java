package ui.controllers;

import db.entities.Diploma;
import db.mapper.GroupMapper;
import db.mapper.StudentMapper;
import db.services.*;
import doc_utils.AppProperties;
import doc_utils.DocWorker;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.Group;
import ui.models.Student;
import ui.utils.AlertBox;
import ui.utils.SpringFXMLLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@Controller("fxmlMainController")
public class FXMLMainController implements Initializable, FXMLStudentController.StudentCallback {

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
    public MenuItem menuItemAccessRequirements;
    @FXML
    public MenuItem menuItemEctsCredits;
    @FXML
    public MenuItem miChooseDB;
    @FXML
    public MenuItem miChooseTemplate;
    @FXML
    public MenuItem miExit;
    @FXML
    public MenuItem miEducationalComponents;
    @FXML
    public MenuItem menuItemEducationalTemplate;
    @FXML
    public MenuItem miClearTables;
    @FXML
    public MenuItem miVariablesList;

    @FXML
    public Button btnGenerate;
    @FXML
    private TableView<Student> tblView;
    @FXML
    private TableColumn<Student, CheckBox> tblColCheckbox;
    @FXML
    private TableColumn<Student, Integer> tblColId;
    @FXML
    private TableColumn<Student, String> tblColStudent;
    @FXML
    private TableColumn<Student, Group> tblColGroup;
    @FXML
    private ComboBox<Group> cbGroup;
    @FXML
    private CheckBox chkboxSelectAll;
    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnClearFilter;
    private ObservableList<Student> studentObservableList = FXCollections.observableArrayList();
    private StudentMapper studentMapper;
    private StudentService studentService;

    private GroupService groupService;
    private GroupMapper groupMapper;

    private DiplomaService diplomaService;
    private DiplomaSubjectService diplomaSubjectService;
    private PreviousDocumentService previousDocumentService;
    private EducationalComponentService educationalComponentService;

    private FXMLStudentController fxmlStudentController;
    private FXMLSettingsController fxmlSettingsController;
    private FXMLVariablesController fxmlVariablesController;

    private DocWorker docWorker;
    private Stage primaryStage;
    private AppProperties appProperties;
    private FXMLEducationalComponentsController fxmlEducationalComponentController;
    private TableCleanerService tableCleanerService;

    @Autowired
    public FXMLMainController(StudentMapper studentMapper, StudentService studentService,
                              DiplomaService diplomaService, DiplomaSubjectService diplomaSubjectService,
                              PreviousDocumentService previousDocumentService,
                              EducationalComponentService educationalComponentService,
                              GroupService groupService, GroupMapper groupMapper,
                              FXMLStudentController fxmlStudentController, FXMLVariablesController fxmlVariablesController, DocWorker docWorker,
                              FXMLSettingsController fxmlSettingsController, AppProperties appProperties, FXMLEducationalComponentsController fxmlEducationalComponentController, TableCleanerService tableCleanerService) {
        this.studentMapper = studentMapper;
        this.studentService = studentService;
        this.diplomaService = diplomaService;
        this.diplomaSubjectService = diplomaSubjectService;
        this.previousDocumentService = previousDocumentService;
        this.educationalComponentService = educationalComponentService;
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.fxmlStudentController = fxmlStudentController;
        this.fxmlVariablesController = fxmlVariablesController;
        this.docWorker = docWorker;
        this.fxmlSettingsController = fxmlSettingsController;
        this.appProperties = appProperties;
        this.fxmlEducationalComponentController = fxmlEducationalComponentController;
        this.tableCleanerService = tableCleanerService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableView();
        setListeners();
        setOnMenuItemAction();
        setComboBox();
    }

    private void initializeStudentList() {
        studentObservableList.clear();

        try {
            List<db.entities.Student> list = studentService.getAll();
            studentObservableList.addAll(studentMapper.map(list));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося отримати інформацію про студентів з БД", e);
        }
    }

    private void setComboBox() {
        try {
            cbGroup.getItems().addAll(groupMapper.map(groupService.getAll()));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося заповнити дані про групи", e);
        }
    }

    private void setOnMenuItemAction() {
        miChooseDB.setOnAction(event -> chooseDB());
        miChooseTemplate.setOnAction(event -> chooseTemplate());
        miEducationalComponents.setOnAction(event -> openEducationalComponentsWindow());
        miClearTables.setOnAction(event -> {
            if (AlertBox.showConfirmationDialog("Підтвердіть операцію",
                    "Ви дійсно бажаєте очистити всі дані(крім статичних даних)?")) {
                try {
                    tableCleanerService.clearAllExeptStaticData();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                    AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                            "Не вдалося очистити дані", e);
                }
            }
        });
        miVariablesList.setOnAction(event -> {
            try {
                fxmlVariablesController.display();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося відкрити вікно зі список змінних", e);
            }
        });
        miExit.setOnAction(event -> System.exit(0));
    }

    private void chooseVariablePattern() {

    }

    private void chooseTemplate() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Виберіть Шаблон");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("*.docx", "*.docx"));
        final File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try {
                appProperties.changeInputFile(file.getPath());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося знайти файл за вказаним шляхом", e);
            }
        }
    }

    private void chooseDB() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Виберіть БД");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("*.db", "*.db"));
        final File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try {
                appProperties.changeDB(file.getPath());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                        "Не вдалося знайти файл за вказаним шляхом", e);
            }
        }
    }

    private void initializeTableView() {
        initializeStudentList();

        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColCheckbox.setCellValueFactory(new PropertyValueFactory<>("select"));
        tblColStudent.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblColGroup.setCellValueFactory(new PropertyValueFactory<>("group"));

        tblView.setItems(studentObservableList);
    }

    private void generateDocuments() {
        long size = studentObservableList.stream().filter(student -> student.getSelect().isSelected()).count();
        if (containsSelectedStudents() && AlertBox.showConfirmationDialog("Підвердіть операцію",
                "Ви справді бажаєте згенерувати додатки для вибраного(-их) студента(-ів)?")) {
            for (Student student :
                    studentObservableList) {
                if (student.getSelect().isSelected()) {
                    try {
                        final String fileName = docWorker.generateDocument(student.getId(), student.getFamilyNameTr());

                        if (size == 1) {
                            docWorker.openFile(fileName);
                        }
                    } catch (IOException | XmlException | SQLException | NullPointerException e) {
                        LOGGER.error(e.getMessage());
                        AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                                "Не вдалося згенерувати інформацію для вибараного студента(-ів)", e);
                    }
                }
            }

            AlertBox.showInformationDialog("Операцію виконано успішно",
                    "Було згенеровано додатки до ДБР " + size + " студентів");
        }
    }

    private void setListeners() {
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
            contextMenu.setStyle("-fx-pref-width: 200px;");

            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    fxmlStudentController.setStudentId(row.getItem().getId());
                    openStudentModalWindow();
                }
            });

            MenuItem editItem = new MenuItem("Edit");
            editItem.setOnAction(e -> {
                fxmlStudentController.setStudentId(row.getItem().getId());
                openStudentModalWindow();
            });

            MenuItem removeItem = new MenuItem("Delete");
            removeItem.setOnAction(e -> {
                if (AlertBox.showConfirmationDialog("Підтвердіть операцію",
                        "Ви дійсно бажаєте видалити вибраного студента?"))
                    deleteStudent(row.getItem());
            });

            contextMenu.getItems().addAll(editItem, removeItem);

            // only display context menu for non-null items:
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(contextMenu)
                            .otherwise((ContextMenu) null));
            return row;
        });

        btnAddStudent.setOnAction(e -> {
            fxmlStudentController.setStudentId(0);
            openStudentModalWindow();
        });
        btnGenerate.setOnMouseClicked(event -> {
            boolean select = false;

            for (Student student :
                    studentObservableList) {
                if (student.getSelect().isSelected()) {
                    select = true;
                    break;
                }
            }

            if (select) {
                generateDocuments();
            } else {
                AlertBox.showErrorDialog("Не вибрано жодного студента",
                        "Щоб згенерувати додатки, виберіть студентів зі списку");
            }
        });

        btnFilter.setOnMouseClicked(event -> {
            Group selectedGroup = cbGroup.getSelectionModel().getSelectedItem();

            if (selectedGroup == null) {
                AlertBox.showWarningDialog("Жодної групи не вибрано",
                        "Виберіть групу для фільтрації студентів");
            } else {
                initializeStudentList();

                ObservableList<Student> observableList = FXCollections.observableArrayList();
                for (Student student :
                        studentObservableList) {
                    observableList.addAll(student);
                }

                studentObservableList.clear();

                for (Student student :
                        observableList) {
                    if (student.getGroup().getName().equals(selectedGroup.getName())) {
                        studentObservableList.add(student);
                    }
                }

                tblView.setItems(studentObservableList);
            }
        });

        btnClearFilter.setOnMouseClicked(event -> initializeStudentList());

        btnAddStudent.setOnAction(e -> {
            fxmlStudentController.setStudentId(0);
            openStudentModalWindow();
        });
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
                event -> openSettingsModalWindow(FXMLSettingsController.Tab.OFFICIAL_DURATION));
        menuItemAccessRequirements.setOnAction(
                event -> openSettingsModalWindow(FXMLSettingsController.Tab.ACCESS_REQUIREMENTS));
        menuItemEctsCredits.setOnAction(
                event -> openSettingsModalWindow(FXMLSettingsController.Tab.ECTS_CREDITS));
        menuItemEducationalTemplate.setOnAction(
                event -> openSettingsModalWindow(FXMLSettingsController.Tab.EDUCATIONAL_TEMPLATE));
    }

    private void deleteStudent(Student student) {
        try {
            Diploma diploma = diplomaService.getByStudentId(student.getId());
            if (diplomaSubjectService.delete(diploma.getDiplomaSubject().getId()) == 1 &&
                    previousDocumentService.delete(student.getPreviousDocument().getId()) == 1 &&
                    studentService.delete(student.getId()) == 1 &&
                    diplomaService.delete(diploma.getId()) == 1) {
                boolean result = true;
                for (db.entities.EducationalComponent component :
                        educationalComponentService.getAllByDiplomaId(diploma.getId())) {
                    if (educationalComponentService.delete(component.getId()) != 1) {
                        result = false;
                    }
                }
                if (result) {
                    tblView.getItems().remove(student);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося видалити студента", e);
        }
    }

    private boolean containsSelectedStudents() {
        return studentObservableList.stream().anyMatch(student -> student.getSelect().isSelected());
    }

    private void openStudentModalWindow() {
        try {
            fxmlStudentController.setStudentCallback(this);
            fxmlStudentController.display();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося відкрити модальне вікно студента", e);
        }
    }

    private void openSettingsModalWindow(FXMLSettingsController.Tab tab) {
        try {
            fxmlSettingsController.setTab(tab);
            fxmlSettingsController.display();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося відкрити модальне вікно студента", e);
        }
    }

    private void openEducationalComponentsWindow() {
        try {
            fxmlEducationalComponentController.display();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося відкрити модальне вікно результати навчання", e);
        }
    }

    private void setChkboxSelectAll() {
        for (Student student :
                studentObservableList) {
            student.getSelect().setSelected(true);
        }
    }

    private void setChboxUnselectAll() {
        for (Student student :
                studentObservableList) {
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
        this.primaryStage = primaryStage;

        primaryStage.setScene(scene);

        primaryStage.setTitle("Генерація додатків до дипломів");

        //setting up min width & height parameters for window
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);

        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    @Override
    public void addStudent(Student student) {
        try {
            studentObservableList.add(studentMapper.map(
                    studentService.getByFullName(student.getFamilyName(), student.getGivenName())));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                    "Не вдалося додати студента у БД", e);
        }
    }

    @Override
    public void updateStudent(Student student) {
        studentObservableList.forEach(student1 -> {
            if (student.getId() == student1.getId()) {
                studentObservableList.set(studentObservableList.indexOf(student1), student);
            }
        });
    }
}
