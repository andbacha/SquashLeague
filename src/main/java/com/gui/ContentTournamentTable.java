package com.gui;

import com.app.MatchResult;
import com.app.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.dynalink.linker.ConversionComparator;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
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
    private TableColumn<Player, String> tableColumnMatches;

    @FXML
    private TableColumn<Player, String> tableColumnWinLoseBalance;

    @FXML
    private TableColumn<Player, String> tableColumnWins;

    @FXML
    private TableColumn<Player, String> tableColumnLoses;

    @FXML
    private TableColumn<Player, String> tableColumnSetBalance;

    @FXML
    private TableColumn<Player, String> tableColumnWonSets;

    @FXML
    private TableColumn<Player, String> tableColumnLostSets;

    @FXML
    private TableColumn<Player, String> tableColumnPointBalance;

    @FXML
    private TableColumn<Player, String> tableColumnWonPoints;

    @FXML
    private TableColumn<Player, String> tableColumnLostPoints;

    @FXML
    private Label labelLegendTitle;

    @FXML
    private Label labelLegendText;

    @FXML
    private Label labelRules;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnStanding.setCellValueFactory(new PropertyValueFactory<>("tournamentPlace"));
        tableColumnPlayer.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        tableColumnMatches.setCellValueFactory(new PropertyValueFactory<>("played"));
        tableColumnWinLoseBalance.setCellValueFactory(new PropertyValueFactory<>("winLoseBalance"));
        tableColumnWins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        tableColumnLoses.setCellValueFactory(new PropertyValueFactory<>("loses"));
        tableColumnSetBalance.setCellValueFactory(new PropertyValueFactory<>("setBalance"));
        tableColumnWonSets.setCellValueFactory(new PropertyValueFactory<>("wonSets"));
        tableColumnLostSets.setCellValueFactory(new PropertyValueFactory<>("lostSets"));
        tableColumnPointBalance.setCellValueFactory(new PropertyValueFactory<>("pointBalance"));
        tableColumnWonPoints.setCellValueFactory(new PropertyValueFactory<>("wonPoints"));
        tableColumnLostPoints.setCellValueFactory(new PropertyValueFactory<>("lostPoints"));

        labelLegendText.setText(
                        "PLD\t-\trozegrane mecze\n" +
                        "MB\t-\tbilans meczów\n" +
                        "W\t-\tzwycięstwa\n" +
                        "L\t-\tporażki\n" +
                        "SB\t-\tbilans setów\n" +
                        "WS\t-\twygrane sety\n" +
                        "LS\t-\tprzegrane sety\n" +
                        "PB\t-\tbilans małych punktów\n" +
                        "WP\t-\twygrane małe punkty\n" +
                        "LP\t-\tprzegrane małe punkty"
        );

        labelRules.setText(
                "O kolejności tabeli decyduje:\n" +
                        "1. Bilans meczów\n" +
                        "2. Zwycięstwa\n" +
                        "3. Bilans setów\n" +
                        "4. Wygrane sety\n" +
                        "5. Bilans małych punktów\n" +
                        "6. Wygrane małe punkty"
        );
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
        tableViewTournament.getColumns().addAll(tableColumnStanding, tableColumnPlayer, tableColumnMatches, tableColumnWinLoseBalance, tableColumnWins, tableColumnLoses, tableColumnSetBalance, tableColumnWonSets, tableColumnLostSets, tableColumnPointBalance, tableColumnWonPoints, tableColumnLostPoints);
        tableViewTournament.getItems().sort(Player.comparator.reversed());
        // fill place column
        for (int i = 0; i < tableViewTournament.getItems().size(); i++) {
            tableViewTournament.getItems().get(i).setTournamentPlace(i + 1);
        }
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
            int wonSets = 0;
            int lostSets = 0;
            int wonPoints = 0;
            int lostPoints = 0;
            for (MatchResult result : results) {
                // get wins and loses
                if (player.getPlayerName().equals(result.getWinner())) {
                    wins++;
                } else if (player.getPlayerName().equals(result.getLoser())) {
                    loses++;
                }
                // get won and lost sets / small points
                if (player.getPlayerName().equals(result.getPlayer1())) {
                    wonSets += result.getPlayer1Sets();
                    lostSets += result.getPlayer2Sets();
                    // get won and lost small points
                    wonPoints += result.getPlayer1FirstSet();
                    wonPoints += result.getPlayer1SecondSet();
                    wonPoints += result.getPlayer1ThirdSet();
                    lostPoints += result.getPlayer2FirstSet();
                    lostPoints += result.getPlayer2SecondSet();
                    lostPoints += result.getPlayer2ThirdSet();

                } else if (player.getPlayerName().equals(result.getPlayer2())) {
                    wonSets += result.getPlayer2Sets();
                    lostSets += result.getPlayer1Sets();
                    // get won and lost small points
                    wonPoints += result.getPlayer2FirstSet();
                    wonPoints += result.getPlayer2SecondSet();
                    wonPoints += result.getPlayer2ThirdSet();
                    lostPoints += result.getPlayer1FirstSet();
                    lostPoints += result.getPlayer1SecondSet();
                    lostPoints += result.getPlayer1ThirdSet();
                }
            }
            player.setWins(wins);
            player.setLoses(loses);
            player.setPlayed(calculatePlayedMatches(player));
            player.setWinLoseBalance(calculateWinLoseBalance(player));
            player.setWonSets(wonSets);
            player.setLostSets(lostSets);
            player.setSetBalance(calculateSetBalance(player));
            player.setWonPoints(wonPoints);
            player.setLostPoints(lostPoints);
            player.setPointBalance(calculatePointBalance(player));
        }
    }

    private int calculatePointBalance(Player player) {
        return player.getWonPoints() - player.getLostPoints();
    }

    private int calculatePlayedMatches(Player player) {
        return player.getWins() + player.getLoses();
    }

    private int calculateWinLoseBalance(Player player) {
        return player.getWins() - player.getLoses();
    }

    private int calculateSetBalance(Player player) {
        return player.getWonSets() - player.getLostSets();
    }

}
