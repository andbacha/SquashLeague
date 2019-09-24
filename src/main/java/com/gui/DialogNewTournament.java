package com.gui;

import com.app.Player;
import com.app.Tournament;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class DialogNewTournament implements Initializable {

    MainMenuController parentController;

    Tournament tournament = new Tournament();

    ObservableList<String> playerNames;

    ArrayList<Player> players = new ArrayList<>();

    @FXML
    private VBox dialogNewSeason;

    @FXML
    private ListView<String> listViewSeasonPlayers;

    @FXML
    private ListView<String> listViewTournamentPlayers;

    @FXML
    private ToggleGroup toggleGroupPointRules;

    @FXML
    private ToggleGroup toggleGroupGroups;

    @FXML
    private TableView<Tournament.PointsPerStanding> tableViewPointsPerStanding;

    @FXML
    private TableColumn<Tournament.PointsPerStanding, Integer> tableColumnStanding;

    @FXML
    private TableColumn<Tournament.PointsPerStanding, String> tableColumnPoints;

    @FXML
    private Button buttonCreateTournament;

    @FXML
    private Button addPlayer;

    @FXML
    private Button removePlayer;

    public void setParentController(MainMenuController parentController) {
        this.parentController = parentController;
    }

    void fillSeasonPlayers(Set<String> players) {
        listViewSeasonPlayers.getItems().addAll(players);
    }

    void addButtonsToListItems() {
        listViewSeasonPlayers.getItems();
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
    void handleButtonCreateTournament(ActionEvent event) throws Exception {
        if (playerNames.size() < 4) {
            displayMessage("Za mało zawodników! Wprowadź co najmniej czterech.");
        } else if (playerNames.size() > 11) {
            displayMessage("Za dużo zawodników! Maksymalna liczba: 11.");
        } else {
            HashMap<String, Player> playersHashMap = parentController.getCurrentSeason().getPlayers();
            for (String playerName : playerNames) {
                players.add(playersHashMap.get(playerName));
            }
            tournament.setPlayers(players);
            tournament.setStartDate();
            parentController.setCurrentTournament(tournament);

            // TODO confirm dialog

            // close window
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tournament = new Tournament();
        listViewSeasonPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewTournamentPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableColumnStanding.setCellValueFactory(new PropertyValueFactory<>("standing"));
        tableColumnPoints.setCellValueFactory(new PropertyValueFactory<>("pointsPerStandingString"));

        tableColumnPoints.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void handleButtonAddPlayer(ActionEvent actionEvent) {
        movePlayer(listViewSeasonPlayers, listViewTournamentPlayers);
    }

    public void handleButtonRemovePlayer(ActionEvent actionEvent) {
        movePlayer(listViewTournamentPlayers, listViewSeasonPlayers);
    }

    private void movePlayer(ListView<String> source, ListView<String> destination) {
        ObservableList<String> selectedPlayers = source.getSelectionModel().getSelectedItems();
        destination.getItems().addAll(selectedPlayers);
        source.getItems().removeAll(selectedPlayers);
        playerNames = listViewTournamentPlayers.getItems();
        int playerQty = listViewTournamentPlayers.getItems().size();
        fillRulesTable(playerQty);
    }

    private void fillRulesTable(int playerQty) {
        tableViewPointsPerStanding.getItems().clear();
        ObservableList<Tournament.PointsPerStanding> standings = FXCollections.observableArrayList();

        for (int i = 0; i < playerQty; i++) {
            standings.add(new Tournament.PointsPerStanding(i + 1, "0"));
        }

        tableViewPointsPerStanding.setItems(null);
        tableViewPointsPerStanding.setItems(standings);
        tableViewPointsPerStanding.getColumns().clear();
        tableViewPointsPerStanding.getColumns().addAll(tableColumnStanding, tableColumnPoints);
    }

    public void editPoints(TableColumn.CellEditEvent<Tournament.PointsPerStanding, String> pointsPerStandingIntegerCellEditEvent) {
        Tournament.PointsPerStanding pointRule = tableViewPointsPerStanding.getSelectionModel().getSelectedItem();
        String newPointValue = pointsPerStandingIntegerCellEditEvent.getNewValue();
        pointRule.setPointsPerStandingString(newPointValue);
        pointRule.setPointsPerStanding(Integer.valueOf(newPointValue));
    }
}
