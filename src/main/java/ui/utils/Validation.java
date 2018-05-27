package ui.utils;

import javafx.scene.control.TextField;

public class Validation {

    public static boolean validateTextField(TextField tf) {
        return tf.getText().trim().length() != 0;
    }

    public static String getTextFieldErrorStyle() {
        return "    -fx-text-box-border: red;" +
                "    -fx-focus-color: red;";
    }
}
