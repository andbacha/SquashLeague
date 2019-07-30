package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationDialogController implements Initializable {

    // PARENT CONTROLLER
    DialogNewSeasonController parentController;

    public DialogNewSeasonController getParentController() {
        return parentController;
    }

    public void setParentController(DialogNewSeasonController parentController) {
        this.parentController = parentController;
    }

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
        parentController.setAnswer(false);
        Stage confirmationDialog = (Stage)((Node)event.getSource()).getScene().getWindow();
        confirmationDialog.close();
    }

    @FXML
    void handleButtonYes(ActionEvent event) {
        parentController.setAnswer(true);
        Stage confirmationDialog = (Stage)((Node)event.getSource()).getScene().getWindow();
        confirmationDialog.close();
    }
}
