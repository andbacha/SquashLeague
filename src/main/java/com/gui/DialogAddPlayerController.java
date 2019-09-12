package com.gui;

import com.app.Player;
import com.app.Season;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.tools.Tool;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class DialogAddPlayerController implements Initializable {

    // PRIVATE FIELDS

    // currently loaded season - non-empty properties filled after loading / creation of new season
    private Season currentSeason = new Season();

    // PARENT CONTROLLER
    ContentPlayersController parentController;

    public ContentPlayersController getParentController() {
        return parentController;
    }

    public void setParentController(ContentPlayersController parentController) {
        this.parentController = parentController;
    }

    /**
     * Receives answer from confirmation dialog after hitting "Utwórz sezon"
     */
    private boolean answer;

    // GUI COMPONENTS

    @FXML
    private VBox dialogAddPlayer;

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
    private Button buttonOK;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (listViewPlayers.getItems().equals(FXCollections.emptyObservableList())) {
            buttonOK.setDisable(true);
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
            buttonOK.setDisable(false);
        }
    }

    @FXML
    void handleButtonOK(ActionEvent event) throws Exception {
        ObservableList<String> players = listViewPlayers.getItems();
        Set<String> playersToAdd = new TreeSet<>();

        // HashMap - players dictionary
        HashMap<String, Player> playerHashMap = parentController.getParentController().getCurrentSeason().getPlayers();
        for (String playerName : players) {
            playerHashMap.put(playerName, new Player(playerName));
            playersToAdd.add(playerName);
        }

        parentController.fillPlayers(playersToAdd);
        // get to MainMenuController via ContentPlayersController, update Player object
        parentController.getParentController().getCurrentSeason().setPlayers(playerHashMap);

        // close window
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleButtonRemovePlayer(ActionEvent event) {
        ObservableList<String> playersToRemove;
        playersToRemove = listViewPlayers.getSelectionModel().getSelectedItems();
        listViewPlayers.getItems().removeAll(playersToRemove);
        if (listViewPlayers.getItems().equals(FXCollections.emptyObservableList())) {
            buttonOK.setDisable(true);
        }
    }
}
