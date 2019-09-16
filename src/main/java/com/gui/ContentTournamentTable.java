package com.gui;

import com.app.Player;
import com.app.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ContentTournamentTable implements Initializable {

    MainMenuController parentController;

    @FXML
    private TableView<Player> tableViewTournament;

    @FXML
    private TableColumn<Player, Integer> tableColumnStanding;

    @FXML
    private TableColumn<Player, String> tableColumnPlayer;

    @FXML
    private TableColumn<Player, Integer> tableColumnPoints;

    @FXML
    private TableColumn<Player, Integer> tableColumnSetBalance;

    @FXML
    private TableColumn<Player, Integer> tableColumnSetsWon;

    @FXML
    private TableColumn<Player, Integer> tableColumnSetsLost;

    @FXML
    private TableColumn<Player, Integer> tableColumnPointBalance;

    @FXML
    private TableColumn<Player, Integer> tableColumnPointsWon;

    @FXML
    private TableColumn<Player, Integer> tableColumnPointsLost;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnStanding.setCellValueFactory(new PropertyValueFactory<>("tournamentPlace"));
        tableColumnPlayer.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        tableColumnPoints.setCellValueFactory(new PropertyValueFactory<>("tournamentPoints"));
        tableColumnPointBalance.setCellValueFactory(new PropertyValueFactory<>("smallPointBalance"));
        tableColumnPointsWon.setCellValueFactory(new PropertyValueFactory<>("wonSmallPoints"));
        tableColumnPointsLost.setCellValueFactory(new PropertyValueFactory<>("lostSmallPoints"));
        tableColumnSetBalance.setCellValueFactory(new PropertyValueFactory<>("setBalance"));
        tableColumnSetsWon.setCellValueFactory(new PropertyValueFactory<>("wonSets"));
        tableColumnSetsLost.setCellValueFactory(new PropertyValueFactory<>("lostSets"));
    }

    public void fillTournamentTable() {
        int playerQty = parentController.getCurrentTournament().getPlayers().size();
        tableViewTournament.getItems().clear();
        ObservableList<Player> players = FXCollections.observableArrayList();

        for (int i = 0; i < playerQty ; i++) {
            players.add(parentController.getCurrentTournament().getPlayers().get(i));
        }

        tableViewTournament.setItems(null);
        tableViewTournament.setItems(players);
        tableViewTournament.getColumns().clear();
        tableViewTournament.getColumns().addAll(tableColumnStanding, tableColumnPlayer, tableColumnPoints, tableColumnPointBalance, tableColumnPointsWon, tableColumnPointsLost, tableColumnSetBalance, tableColumnSetsWon, tableColumnSetsLost);
    }

    public void setParentController(MainMenuController parentController) {
        this.parentController = parentController;
    }
}
