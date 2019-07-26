package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WarningDialogController implements Initializable {

    boolean answer;

    @FXML
    private Label labelWarningDialogMessage;

    @FXML
    private Button buttonYes;

    @FXML
    private Button buttonNo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void handleButtonNo(ActionEvent event) {

    }

    @FXML
    void handleButtonYes(ActionEvent event) {

    }
}
