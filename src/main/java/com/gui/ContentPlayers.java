package com.gui;

import com.app.Player;
import com.app.Season;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class ContentPlayers implements Initializable {

    MainMenuController parentController;

    Set<String> players;

    @FXML
    private Button buttonAddPlayer;

    @FXML
    private Button buttonModifyPlayer;

    @FXML
    private Button buttonRemovePlayer;

    @FXML
    private ListView<String> listViewPlayers;

    public MainMenuController getParentController() {
        return parentController;
    }

    public void setParentController(MainMenuController parentController) {
        this.parentController = parentController;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void setPlayers(Set<String> players) {
        this.players = players;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // list all players
    }

    // PUBLIC METHODS

    void fillPlayers(Set<String> players) {
        listViewPlayers.getItems().addAll(players);
    }

    @FXML
    void addPlayer(ActionEvent event) {

    }

    @FXML
    void modifyPlayer(ActionEvent event) {

    }

    @FXML
    void removePlayer(ActionEvent event) {

    }
}
