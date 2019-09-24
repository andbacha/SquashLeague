package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoDialogController implements Initializable {

    @FXML
    Button buttonOk;

    @FXML
    Label text;

    public void handleButtonOk(ActionEvent actionEvent) {
        // close window
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text.setText(
                "DziadoligaEditor v. 0.1\n" +
                "by andbacha\n" +
                "© 2019"
        );
    }

    public void setMessageText(String message) {
        text.setText(message);
    }
}
