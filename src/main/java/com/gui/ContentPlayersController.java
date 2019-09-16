package com.gui;

import com.app.Player;
import com.app.Season;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class ContentPlayersController implements Initializable {

    MainMenuController parentController;

    Set<String> players;

    Season currentSeason;

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
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    // PUBLIC METHODS

    /**
     * Fill table of playerNames in MainMenu content
     * @param players playerNames' names
     */
    void fillPlayers(Set<String> players) {
        listViewPlayers.getItems().addAll(players);
    }

    @FXML
    void addPlayer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DialogAddPlayer.fxml"));
        Parent root = loader.load();

        Scene modalDialogScene = new Scene(root);

        // Provide MainMenuController object to child controller
        DialogAddPlayerController childController = loader.getController();
        childController.setParentController(this);

        // Set and display stage
        Stage modalDialog = new Stage();
        modalDialog.setScene(modalDialogScene);
        modalDialog.initModality(Modality.APPLICATION_MODAL);
        modalDialog.setTitle("Dodaj zawodnika");
        modalDialog.setResizable(false);
        modalDialog.showAndWait();
    }

    @FXML
    void modifyPlayer(ActionEvent event) throws IOException {
        ObservableList<String> playerToModify;
        playerToModify = listViewPlayers.getSelectionModel().getSelectedItems();
        if (playerToModify.size() == 1) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("DialogModifyPlayer.fxml"));
            Parent root = loader.load();

            Scene modalDialogScene = new Scene(root);

            // Provide MainMenuController object to child controller
            DialogModifyPlayer childController = loader.getController();
            childController.setCurrentPlayerName(playerToModify.get(0));
            childController.setParentController(this);

            // Set and display stage
            Stage modalDialog = new Stage();
            modalDialog.setScene(modalDialogScene);
            modalDialog.initModality(Modality.APPLICATION_MODAL);
            modalDialog.setTitle("Modyfikuj zawodnika");
            modalDialog.setResizable(false);
            modalDialog.showAndWait();

            // clear and fill new list
            listViewPlayers.getItems().clear();
            fillPlayers(players);
        }
    }

    @FXML
    void removePlayer(ActionEvent event) {
        ObservableList<String> playersToRemove;
        playersToRemove = listViewPlayers.getSelectionModel().getSelectedItems();
        for (String player : playersToRemove) {
            if (parentController.getCurrentSeason().getPlayers().keySet().contains(player)) {
                parentController.getCurrentSeason().getPlayers().remove(player);
            }
        }
        listViewPlayers.getItems().removeAll(playersToRemove);
        if (listViewPlayers.getItems().equals(FXCollections.emptyObservableList())) {
            buttonRemovePlayer.setDisable(true);
        }
    }
}
