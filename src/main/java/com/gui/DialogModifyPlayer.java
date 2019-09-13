package com.gui;

import com.app.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DialogModifyPlayer implements Initializable {

    ContentPlayersController parentController;

    @FXML
    private TextField textFieldName;

    @FXML
    private Button buttonOK;

    String currentPlayerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void handleButtonOK(ActionEvent event) {
        String newName = textFieldName.getText();
        Player modifiedPlayer = parentController.getParentController().getCurrentSeason().getPlayers().get(currentPlayerName);
        modifiedPlayer.setPlayerName(newName);
        parentController.getParentController().getCurrentSeason().getPlayers().remove(currentPlayerName);
        parentController.getParentController().getCurrentSeason().getPlayers().put(newName, modifiedPlayer);
        // close window
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setCurrentPlayerName(String playerName) {
        currentPlayerName = playerName;
        textFieldName.setText(playerName);
    }

    public void setParentController(ContentPlayersController parentController) {
        this.parentController = parentController;
    }
}
