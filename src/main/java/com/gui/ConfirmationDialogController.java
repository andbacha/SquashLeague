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
    void handleButtonYes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogNewSeason.fxml"));
        loader.load();

        DialogNewSeasonController controller = loader.getController();
        controller.answerFromConfirmationDialog(true);

        Stage confirmationDialog = (Stage)((Node)event.getSource()).getScene().getWindow();
        confirmationDialog.close();
    }
}
