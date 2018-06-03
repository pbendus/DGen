package ui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class AlertBox {

    public static void showInformationDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public static void showWarningDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public static void showErrorDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public static void showExceptionDialog(String header, String content, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Інформація про переривання:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);


        alert.showAndWait();
    }

    public static boolean showConfirmationDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}

