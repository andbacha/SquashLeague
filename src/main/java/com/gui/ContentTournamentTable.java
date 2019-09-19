package com.gui;

import com.app.MatchResult;
import com.app.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContentTournamentTable implements Initializable {

    MainMenuController parentController;

    @FXML
    private TableView<Player> tableViewTournament;

    @FXML
    private TableColumn<Player, String> tableColumnStanding;

    @FXML
    private TableColumn<Player, String> tableColumnPlayer;

    @FXML
    private TableColumn<Player, String> tableColumnWinLoseBalance;

    @FXML
    private TableColumn<Player, String> tableColumnWins;

    @FXML
    private TableColumn<Player, String> tableColumnLoses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnStanding.setCellValueFactory(new PropertyValueFactory<>("tournamentPlace"));
        tableColumnPlayer.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        tableColumnWinLoseBalance.setCellValueFactory(new PropertyValueFactory<>("winLoseBalance"));
        tableColumnWins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        tableColumnLoses.setCellValueFactory(new PropertyValueFactory<>("loses"));
    }

    public void fillTournamentTable() {
        calculateTable();
        int playerQty = parentController.getCurrentTournament().getPlayers().size();
        tableViewTournament.getItems().clear();
        ObservableList<Player> players = FXCollections.observableArrayList();

        for (int i = 0; i < playerQty ; i++) {
            players.add(parentController.getCurrentTournament().getPlayers().get(i));
        }

        tableViewTournament.setItems(null);
        tableViewTournament.setItems(players);
        tableViewTournament.getColumns().clear();
        tableViewTournament.getColumns().addAll(tableColumnStanding, tableColumnPlayer, tableColumnWinLoseBalance, tableColumnWins, tableColumnLoses);
        tableViewTournament.getItems().sort(Player.comparator.reversed());
    }

    public void setParentController(MainMenuController parentController) {
        this.parentController = parentController;
    }

    public void calculateTable() {
        ArrayList<MatchResult> results = parentController.getCurrentTournament().getResults();
        calculateTableContent(results);
    }

    private void calculateTableContent(ArrayList<MatchResult> results) {
        for (Player player : parentController.getCurrentTournament().getPlayers()) {
            int wins = 0;
            int loses = 0;
            for (MatchResult result : results) {
                if (player.getPlayerName().equals(result.getWinner())) { wins++; }
                else if (player.getPlayerName().equals(result.getLoser())) { loses++; }
            }
            player.setWins(wins);
            player.setLoses(loses);
            player.setWinLoseBalance(calculateWinLoseBalance(player));
        }
    }

    private int calculateWinLoseBalance(Player player) {
        return player.getWins() - player.getLoses();
    }
}
