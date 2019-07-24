package com.gui;

import com.app.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.tools.Tool;

public class DialogNewSeason {

    @FXML
    private Button buttonImportPlayers;

    @FXML
    private TextField textFieldPlayerName;

    @FXML
    private Button buttonAddPlayer;

    @FXML
    private Button buttonRemovePlayer;

    @FXML
    private ListView<String> listViewPlayers;

    @FXML
    private Button buttonCreateTournament;

    @FXML

    public void initialize() {
        listViewPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void handleButtonAddPlayer(ActionEvent event) {
        String playerName = textFieldPlayerName.getText();
        if (playerName.equals("")) {
            textFieldPlayerName.setPromptText("Wprowadzono pustą nazwę!");
        } else {
            listViewPlayers.getItems().add(playerName);
            textFieldPlayerName.clear();
            textFieldPlayerName.setPromptText("Imię / pseudonim");
        }
    }

    @FXML
    void handleButtonCreateTournament(ActionEvent event) {

    }

    @FXML
    void handleButtonRemovePlayer(ActionEvent event) {
        ObservableList<String> playersToRemove;
        playersToRemove = listViewPlayers.getSelectionModel().getSelectedItems();
        listViewPlayers.getItems().removeAll(playersToRemove);
    }

}
