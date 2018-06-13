package ui.controllers;

import db.mappers.DiplomaMapper;
import db.services.DiplomaService;
import db.services.EducationalComponentService;
import doc_utils.DocWorker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.controlsfx.dialog.Dialogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.Diploma;
import ui.models.StudentWithAVG;
import ui.utils.AlertBox;
import ui.utils.SpringFXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Controller("fxmlAVGController")
public class FXMLAVGController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger();
    @FXML
    public TableView<StudentWithAVG> tblResults;
    @FXML
    public TableColumn<StudentWithAVG, Integer> tblColId;
    @FXML
    public TableColumn<StudentWithAVG, String> tblColName;
    @FXML
    public TableColumn<StudentWithAVG, String> tblColAVG;
    @FXML
    public Button btnGenerate;
    private Stage stage;
    private DiplomaService diplomaService;
    private DiplomaMapper diplomaMapper;
    private EducationalComponentService educationalComponentService;
    private ObservableList<StudentWithAVG> studentWithAVGObservableList = FXCollections.observableArrayList();
    private DocWorker docWorker;

    private int modeOfStudyId;

    @Autowired
    public FXMLAVGController(DiplomaService diplomaService, DiplomaMapper diplomaMapper,
                             EducationalComponentService educationalComponentService, DocWorker docWorker) {
        this.diplomaService = diplomaService;
        this.diplomaMapper = diplomaMapper;
        this.educationalComponentService = educationalComponentService;
        this.docWorker = docWorker;
        this.modeOfStudyId = -1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDiploma()
                .getStudent().fullNameProperty().get()));
        tblColAVG.setCellValueFactory(new PropertyValueFactory<>("avg"));

        final List<StudentWithAVG> studentWithAVGS = new ArrayList<>();

        final Task<Void> service = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    studentWithAVGObservableList.clear();
                    final List<Diploma> diplomas = diplomaMapper.map(diplomaService.getAll());
                    for (Diploma diploma :
                            diplomas) {
                        if (diploma.getStudent().getModeOfStudy().getId() == modeOfStudyId) {
                            int diplomaId = diploma.getId();
                            double avg = educationalComponentService.getAVG(diploma.getId());

                            final StudentWithAVG studentWithAVG = new StudentWithAVG(diploma,
                                    Double.parseDouble(new DecimalFormat("#0.000").format(avg)),
                                    educationalComponentService.getNumberOfFives(diplomaId),
                                    educationalComponentService.getNumberOfFours(diplomaId),
                                    educationalComponentService.getNumberOfThree(diplomaId),
                                    educationalComponentService.getAllNotZeroByDiplomaId(diplomaId).size());

                            if (avg != 0) {
                                studentWithAVGS.add(studentWithAVG);
                            }
                        }
                    }
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                    AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                            "Не вдалося завантажити дані", e);
                    btnGenerate.setDisable(false);
                }
                return null;
            }
        };

        Dialogs.create()
                .owner(stage)
                .title("Progress Dialog")
                .masthead("Завантаження даних")
                .showWorkerProgress(service);

        btnGenerate.setDisable(true);

        final Thread thread = new Thread(service);
        thread.start();

        service.setOnSucceeded(event -> {
            if (btnGenerate.isDisabled()) {
                Collections.sort(studentWithAVGS);
                for (int i = 0; i < studentWithAVGS.size(); i++) {
                    studentWithAVGS.get(i).setId(i + 1);
                }
                studentWithAVGObservableList.addAll(studentWithAVGS);
                tblResults.setItems(studentWithAVGObservableList);
            }
            btnGenerate.setDisable(false);
        });


        btnGenerate.setOnAction(event -> {
            final Task<Void> generateService = new Task<Void>() {
                @Override
                protected Void call() {
                    try {
                        docWorker.openFile(docWorker.generateRatingDocument(studentWithAVGS));
                    } catch (IOException | XmlException | SQLException e) {
                        LOGGER.error(e.getMessage());
                        AlertBox.showExceptionDialog("Роботу програми зупинено перериванням",
                                "Не вдалося згенерувати документ", e);
                    }
                    return null;
                }
            };
            Dialogs.create()
                    .owner(stage)
                    .title("Progress Dialog")
                    .masthead("Генерація документу")
                    .showWorkerProgress(generateService);

            btnGenerate.setDisable(true);
            final Thread threadGenerate = new Thread(generateService);
            threadGenerate.start();

            generateService.setOnSucceeded(event1 -> {
                if (btnGenerate.isDisabled()) {
                    if (btnGenerate.isDisabled()) {
                        AlertBox.showInformationDialog("Операцію виконано успішно",
                                "Документ з результами про успішність згенерований");
                    }
                }
                btnGenerate.setDisable(false);
            });

            generateService.setOnFailed(event1 -> {
                AlertBox.showErrorDialog("Роботу програми зупинено перериванням",
                        "Не вдалося згенерувати документ");
                btnGenerate.setDisable(false);
            });

            generateService.setOnCancelled(event1 -> btnGenerate.setDisable(false));
        });
    }

    void display() throws Exception {
        final Parent root = SpringFXMLLoader.create()
                .applicationContext(Main.getContext())
                .location(FXMLStudentController.class
                        .getResource("../../fxml/avg.fxml"))
                .load();

        final Scene scene = new Scene(root);

        stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Рейтинг студентів");

        //setting up min width & height parameters for window
        stage.setMinWidth(600);
        stage.setMinHeight(450);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @SuppressWarnings("unused")
    private void closeWindow() {
        stage.close();
    }

    public void setModeOfStudyId(int modeOfStudyId) {
        this.modeOfStudyId = modeOfStudyId;
    }
}
