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
import java.util.HashMap;
import java.util.ResourceBundle;

public class DialogNewSeasonController implements Initializable {

    // PRIVATE FIELDS

    // currently loaded season - non-empty properties filled after loading / creation of new season
    private Season currentSeason = new Season();

    // DATA FROM OTHER CONTROLLERS

    // ConfirmationDialogController

    /**
     * Receives answer from confirmation dialog after hitting "Utwórz sezon"
     */
    boolean answer;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (listViewPlayers.getItems().equals(FXCollections.emptyObservableList())) {
            buttonCreateTournament.setDisable(true);
        }
    }

    /**
     * Displays confirmation / warning dialog.
     * @throws Exception
     */
    public boolean displayConfirmationDialog() throws Exception {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ConfirmationDialog.fxml"));
        window.setScene(new Scene(root));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Potwierdź");
        window.setResizable(false);
        window.showAndWait();
        return answer;
    }

    public void answerFromConfirmationDialog(boolean answer) {
        this.answer = answer;
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
    void handleButtonCreateSeason(ActionEvent event) throws Exception {
        ObservableList<String> players = listViewPlayers.getItems();
        try {
            int targetPoints = Integer.parseInt(textFieldTargetPoints.getText());
            if (textFieldTargetPoints.getText().equals("")) {
                textFieldTargetPoints.setPromptText("Wpisz docelową liczbę punktów!");
            } else {
                // display confirmation dialog, if answer is yes - proceed
                if (displayConfirmationDialog()) {
                    // HashMap - players dictionary
                    HashMap<String, Player> playerHashMap = new HashMap<>();
                    for (String playerName : players) {
                        playerHashMap.put(playerName, new Player(playerName));
                    }

                    // create new season object (currentSeason)
                    currentSeason.setPlayers(playerHashMap);
                    currentSeason = new Season(LocalDate.now(), playerHashMap);
                }

                // close window

                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        } catch (NumberFormatException e) {
            textFieldTargetPoints.clear();
            textFieldTargetPoints.setPromptText("Wpisz poprawną liczbę punktów!");
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
