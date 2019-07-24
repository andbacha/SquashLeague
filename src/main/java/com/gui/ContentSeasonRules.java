package com.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ContentSeasonRules {

    @FXML
    private TextField textFieldTargetPoints;

    public TextField getTextFieldTargetPoints() {
        return textFieldTargetPoints;
    }

    public void setTextFieldTargetPoints(TextField textFieldTargetPoints) {
        this.textFieldTargetPoints = textFieldTargetPoints;
    }
}
