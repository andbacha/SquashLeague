package com.gui;

import com.app.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Set;

public class ContentSeasonTable {

    private MainMenuController parentController;

    private Set<Player> players;

    @FXML
    private TableView<Player> tableViewSeason;

    @FXML TableColumn<Player, String> col

    public MainMenuController getParentController() {
        return parentController;
    }

    public void setParentController(MainMenuController parentController) {
        this.parentController = parentController;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    // PUBLIC METHODS

    public void fillSeasonTable() {
        ObservableList<Player> playerObservableList = FXCollections.observableArrayList();
        playerObservableList.addAll(players);
        tableViewSeason.setItems(playerObservableList);
        tableViewSeason.getColumns().addAll()
    }

}
