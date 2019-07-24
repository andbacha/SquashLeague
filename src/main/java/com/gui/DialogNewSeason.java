package com.gui;

import com.app.Player;
import com.app.Season;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import javax.tools.Tool;
import java.time.LocalDate;
import java.util.HashMap;

public class DialogNewSeason {

    // PRIVATE FIELDS

    // currently loaded season - non-empty properties filled after loading / creation of new season
    private Season currentSeason = new Season();

    // GUI COMPONENTS

    @FXML
    private VBox dialogNewSeason;

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
    private TextField textFieldTargetPoints;

    @FXML
    private Button buttonCreateTournament;

    @FXML

    public void initialize() {
        listViewPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (listViewPlayers.getItems().equals(FXCollections.emptyObservableList())) {
            buttonCreateTournament.setDisable(true);
        }
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
            buttonCreateTournament.setDisable(false);
        }
    }

    @FXML
    void handleButtonCreateSeason(ActionEvent event) {
        ObservableList<String> players = listViewPlayers.getItems();
        try {
            int targetPoints = Integer.parseInt(textFieldTargetPoints.getText());
            if (textFieldTargetPoints.getText().equals("")) {
                textFieldTargetPoints.setPromptText("Wpisz docelową liczbę punktów!");
            } else {
                // HashMap - players dictionary
                HashMap<String, Player> playerHashMap = new HashMap<>();
                for (String playerName : players) {
                    playerHashMap.put(playerName, new Player(playerName));
                }

                // create new season object (currentSeason)
                currentSeason.setPlayers(playerHashMap);
                currentSeason = new Season(LocalDate.now(), playerHashMap);
                dialogNewSeason.setVisible(false);
            }
        } catch (NumberFormatException e) {
            textFieldTargetPoints.clear();
            textFieldTargetPoints.setPromptText("Wpisz poprawną liczbę punktów!");
        } catch (Exception e) {
            System.out.println("Inny błąd");
        }
    }

    @FXML
    void handleButtonRemovePlayer(ActionEvent event) {
        ObservableList<String> playersToRemove;
        playersToRemove = listViewPlayers.getSelectionModel().getSelectedItems();
        listViewPlayers.getItems().removeAll(playersToRemove);
        if (listViewPlayers.getItems().equals(FXCollections.emptyObservableList())) {
            buttonCreateTournament.setDisable(true);
        }
    }

}
