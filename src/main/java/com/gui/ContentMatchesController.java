package com.gui;

import com.app.Match;
import com.app.MatchResult;
import com.app.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ContentMatchesController implements Initializable {

    MainMenuController parentController;

    @FXML
    private VBox vBoxContentMatches;

    @FXML
    private Button buttonRemoveResult;

    @FXML
    private CheckBox checkBoxShowSets;

    @FXML
    private TableView<MatchResult> tableViewMatches;

    @FXML
    private TableColumn<MatchResult, String> tableColumnPlayer1;

    @FXML
    private TableColumn<MatchResult, String> tableColumnPlayer2;

    @FXML
    private TableColumn<MatchResult, String> tableColumnResult1;

    @FXML
    private TableColumn<MatchResult, String> tableColumnResult2;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet1P1;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet1P2;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet2P1;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet2P2;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet3P1;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet3P2;

    @FXML
    void handleButtonRemoveResult(ActionEvent event) {

    }

    @FXML
    void handleCheckBoxShowSets(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnPlayer1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        tableColumnPlayer2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        tableColumnResult1.setCellValueFactory(new PropertyValueFactory<>("player1Sets"));
        tableColumnResult2.setCellValueFactory(new PropertyValueFactory<>("player2Sets"));
        tableColumnSet1P1.setCellValueFactory(new PropertyValueFactory<>("player1FirstSet"));
        tableColumnSet1P2.setCellValueFactory(new PropertyValueFactory<>("player2FirstSet"));
        tableColumnSet2P1.setCellValueFactory(new PropertyValueFactory<>("player1SecondSet"));
        tableColumnSet2P2.setCellValueFactory(new PropertyValueFactory<>("player2SecondSet"));
        tableColumnSet3P1.setCellValueFactory(new PropertyValueFactory<>("player1ThirdSet"));
        tableColumnSet3P2.setCellValueFactory(new PropertyValueFactory<>("player2ThirdSet"));
    }

    public void fillTournamentTable() {
        int matchQty = parentController.getCurrentTournament().getMatches().size();
        tableViewMatches.getItems().clear();
        ObservableList<MatchResult> matches = FXCollections.observableArrayList();

        for (int i = 0; i < matchQty ; i++) {
            matches.add(parentController.getCurrentTournament().getMatches().get(i).getResult());
        }

        tableViewMatches.setItems(null);
        tableViewMatches.setItems(matches);
        tableViewMatches.getColumns().clear();
        tableViewMatches.getColumns().addAll(tableColumnPlayer1, tableColumnPlayer2, tableColumnResult1, tableColumnResult2, tableColumnSet1P1, tableColumnSet1P2, tableColumnSet2P1, tableColumnSet2P2, tableColumnSet3P1, tableColumnSet3P2);
    }

    public MainMenuController getParentController() {
        return parentController;
    }

    public void setParentController(MainMenuController parentcontroller) {
        this.parentController = parentcontroller;
    }
}
