package ui.utils;

import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProgressForm {
    public final ProgressBar pb = new ProgressBar();
    private final Stage dialogStage;

    public ProgressForm() {
        dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        pb.setProgress(-1F);

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(pb);

        Scene scene = new Scene(hb);
        dialogStage.setScene(scene);
    }

    public void activateProgressBar(final Task<?> task) {
        pb.progressProperty().bind(task.progressProperty());
        dialogStage.show();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
}