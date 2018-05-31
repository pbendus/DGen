package ui.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Validation {

    public static boolean validateTextField(TextField tf) {
        return tf.getText().trim().length() != 0;
    }

    public static boolean validateComboBox(ComboBox cb) {
        return cb.getSelectionModel().getSelectedItem() != null;
    }

    public static boolean validateDatePicker(DatePicker dp) {
        return dp.getValue() != null;
    }

    public final static String getTextFieldErrorStyle() {
        return "    -fx-text-box-border: red;" +
                "    -fx-focus-color: red;";
    }

    public final static String getComboBoxErrorStyle() {
        return "-fx-border-color: red;";
    }

    public final static String getDatePickerErrorStyle() {
        return "-fx-border-color: red;";
    }
}
