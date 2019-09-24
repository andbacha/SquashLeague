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

    // PARENT CONTROLLER
    MainMenuController parentController;

    public MainMenuController getParentController() {
        return parentController;
    }

    public void setParentController(MainMenuController parentController) {
        this.parentController = parentController;
    }

    /**
     * Receives answer from confirmation dialog after hitting "Utwórz sezon"
     */
    private boolean answer;

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
    private TextField textFieldRounds;

    @FXML
    private Button buttonCreateTournament;

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConfirmationDialog.fxml"));
        Parent root = loader.load();

        Scene confirmDialogScene = new Scene(root);

        // Provide DialogNewSeasonController object to child controller
        ConfirmationDialogController childController = loader.getController();
        childController.setParentController(this);

        // Set and display stage
        Stage modalDialog = new Stage();
        modalDialog.setScene(confirmDialogScene);
        modalDialog.initModality(Modality.APPLICATION_MODAL);
        modalDialog.setTitle("Potwierdź");
        modalDialog.setResizable(false);
        modalDialog.showAndWait();

        return answer;
    }

    public void displayMessage(String message) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InfoDialog.fxml"));
        Parent root = loader.load();

        Scene infoDialogScene = new Scene(root);

        // Provide DialogNewSeasonController object to child controller
        InfoDialogController childController = loader.getController();
        childController.setMessageText(message);

        // Set and display stage
        Stage modalDialog = new Stage();
        modalDialog.setScene(infoDialogScene);
        modalDialog.initModality(Modality.APPLICATION_MODAL);
        modalDialog.setTitle("Uwaga!");
        modalDialog.setResizable(false);
        modalDialog.show();
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
            int targetPoints = Integer.parseInt(textFieldRounds.getText());
            if (textFieldRounds.getText().equals("")) {
                textFieldRounds.setPromptText("Wpisz docelową liczbę kolejek!");
            } else if (listViewPlayers.getItems().size() < 4) {
                displayMessage("Za mało zawodników! Wprowadź co najmniej czterech.");
            } else {
                // display confirmation dialog, if answer is yes - proceed
                if (displayConfirmationDialog()) {
                    // HashMap - playerNames dictionary
                    HashMap<String, Player> playerHashMap = new HashMap<>();
                    for (String playerName : players) {
                        playerHashMap.put(playerName, new Player(playerName));
                    }

                    // create new season object and pass it to MainMenuController
                    parentController.setCurrentSeason(new Season(LocalDate.now(), playerHashMap));

                    // handle 'yes', TODO: redundant variable?
                    parentController.setSeasonCreated(true);

                    // close window
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                } else {
                    // handle 'no'
                    parentController.setSeasonCreated(false);
                }


            }
        } catch (NumberFormatException e) {
            textFieldRounds.clear();
            textFieldRounds.setPromptText("Wpisz poprawną liczbę kolejek!");
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
