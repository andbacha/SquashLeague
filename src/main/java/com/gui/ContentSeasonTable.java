package com.gui;

import com.app.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class ContentSeasonTable implements Initializable {

    private MainMenuController parentController;

    private HashMap<String, Player> players;

    @FXML
    private TableView<Player> tableViewSeason;

    @FXML
    private TableColumn<Player, Integer> columnPlace;

    @FXML
    private TableColumn<Player, String> columnPlayer;

    @FXML
    private TableColumn<Player, Integer> columnPoints;

    public MainMenuController getParentController() {
        return parentController;
    }

    public void setParentController(MainMenuController parentController) {
        this.parentController = parentController;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnPlace.setCellValueFactory(new PropertyValueFactory<>("seasonPlace"));
        columnPlayer.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        columnPoints.setCellValueFactory(new PropertyValueFactory<>("seasonPoints"));
    }

    // PUBLIC METHODS

    public ObservableList<Player> getPlayer() {
        ObservableList<Player> playerObservableList = FXCollections.observableArrayList();
        for (Player player : players.values()) {
            playerObservableList.add(player);
        }

        return playerObservableList;
    }

    public void fillSeasonTable() {
        tableViewSeason.setItems(getPlayer());
//        tableViewSeason.getColumns().addAll(columnPlace, columnPlayer, columnPoints);
    }
}
