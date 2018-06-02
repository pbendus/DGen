package ui.utils;

import javafx.scene.control.Alert;

public class AlertBox {
    public static void showErrorDialog(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText(title);
        alert.showAndWait();
    }

    public static void showInfoDialog(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText(title);
        alert.showAndWait();
    }
}
