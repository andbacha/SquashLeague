package com.gui;

import com.app.MatchResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContentMatchesController implements Initializable {

    MainMenuController parentController;

    @FXML
    private VBox vBoxContentMatches;

    @FXML
    private Button buttonRemoveResult;

    @FXML
    private TableView<MatchResult> tableViewMatches;

    @FXML
    private TableColumn<MatchResult, String> tableColumnPlayer1;

    @FXML
    private TableColumn<MatchResult, String> tableColumnResult;

    @FXML
    private TableColumn<MatchResult, String> tableColumnPlayer2;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet1;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet2;

    @FXML
    private TableColumn<MatchResult, String> tableColumnSet3;

    @FXML
    void handleButtonRemoveResult(ActionEvent event) {
        MatchResult resultToRemove = tableViewMatches.getSelectionModel().getSelectedItem();
        resultToRemove.setSet1("0:0");
        resultToRemove.setSet2("0:0");
        resultToRemove.setSet3("0:0");
        resultToRemove.setPlayer1FirstSet(0);
        resultToRemove.setPlayer2FirstSet(0);
        resultToRemove.setPlayer1SecondSet(0);
        resultToRemove.setPlayer2SecondSet(0);
        resultToRemove.setPlayer1ThirdSet(0);
        resultToRemove.setPlayer2ThirdSet(0);
        setResult(resultToRemove);
        fillMatches();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnPlayer1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        tableColumnPlayer2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        tableColumnResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        tableColumnSet1.setCellValueFactory(new PropertyValueFactory<>("set1"));
        tableColumnSet2.setCellValueFactory(new PropertyValueFactory<>("set2"));
        tableColumnSet3.setCellValueFactory(new PropertyValueFactory<>("set3"));

        tableColumnSet1.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnSet2.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnSet3.setCellFactory(TextFieldTableCell.forTableColumn());

        tableColumnPlayer1.setStyle("-fx-alignment: CENTER-RIGHT");
        tableColumnResult.setStyle("-fx-alignment: CENTER");
        tableColumnPlayer2.setStyle("-fx-alignment: CENTER-LEFT");
        tableColumnSet1.setStyle("-fx-alignment: CENTER");
        tableColumnSet2.setStyle("-fx-alignment: CENTER");
        tableColumnSet3.setStyle("-fx-alignment: CENTER");
    }

    public void fillMatches() {
        int matchQty = parentController.getCurrentTournament().getMatches().size();
        tableViewMatches.getItems().clear();
        ObservableList<MatchResult> matches = FXCollections.observableArrayList();

        for (int i = 0; i < matchQty ; i++) {
            matches.add(parentController.getCurrentTournament().getMatches().get(i).getResult());
        }

        tableViewMatches.setItems(null);
        tableViewMatches.setItems(matches);
        tableViewMatches.getColumns().clear();
        tableViewMatches.getColumns().addAll(tableColumnPlayer1, tableColumnResult, tableColumnPlayer2, tableColumnSet1, tableColumnSet2, tableColumnSet3);
    }

    public MainMenuController getParentController() {
        return parentController;
    }

    public void setParentController(MainMenuController parentController) {
        this.parentController = parentController;
    }

    public void editSet1(TableColumn.CellEditEvent<MatchResult, String> matchResultStringCellEditEvent) {
        MatchResult result = tableViewMatches.getSelectionModel().getSelectedItem();
        String newResult = matchResultStringCellEditEvent.getNewValue();
        // validate input
        if (newResult.matches("^\\d{1,2}:\\d{1,2}$")) {
            result.setSet1(newResult);
            String[] newResultStrArray = newResult.split(":");
            // parse points
            result.setPlayer1FirstSet(Integer.valueOf(newResultStrArray[0]));
            result.setPlayer2FirstSet(Integer.valueOf(newResultStrArray[1]));
        } else if (newResult.equals("")) {
            result.setPlayer1FirstSet(0);
            result.setPlayer2FirstSet(0);
            result.setSet1("0:0");
        } else {
            result.setSet1(result.getSet1());
        }

        commitChanges(result);
    }

    public void editSet2(TableColumn.CellEditEvent<MatchResult, String> matchResultStringCellEditEvent) {
        MatchResult result = tableViewMatches.getSelectionModel().getSelectedItem();
        String newResult = matchResultStringCellEditEvent.getNewValue();
        // validate input
        if (newResult.matches("^\\d{1,2}:\\d{1,2}$")) {
            result.setSet2(newResult);
            String[] newResultStrArray = newResult.split(":");
            // parse points
            result.setPlayer1SecondSet(Integer.valueOf(newResultStrArray[0]));
            result.setPlayer2SecondSet(Integer.valueOf(newResultStrArray[1]));

        } else if (newResult.equals("")) {
            result.setPlayer1SecondSet(0);
            result.setPlayer2SecondSet(0);
            result.setSet2("0:0");
        } else {
            result.setSet2(result.getSet2());
        }

        commitChanges(result);
    }

    public void editSet3(TableColumn.CellEditEvent<MatchResult, String> matchResultStringCellEditEvent) {
        MatchResult result = tableViewMatches.getSelectionModel().getSelectedItem();
        String newResult = matchResultStringCellEditEvent.getNewValue();
        // validate input
        if (newResult.matches("^\\d{1,2}:\\d{1,2}$")) {
            result.setSet3(newResult);
            String[] newResultStrArray = newResult.split(":");
            // parse points
            result.setPlayer1ThirdSet(Integer.valueOf(newResultStrArray[0]));
            result.setPlayer2ThirdSet(Integer.valueOf(newResultStrArray[1]));
        } else if (newResult.equals("")) {
            result.setPlayer1ThirdSet(0);
            result.setPlayer2ThirdSet(0);
            result.setSet3("0:0");
        } else {
            result.setSet3(result.getSet3());
        }

        commitChanges(result);
    }

    private String setResult(int s1P1, int s1P2, int s2P1, int s2P2, int s3P1, int s3P2) {
        int p1Result = 0, p2Result = 0;

        if (s1P1 > s1P2) { p1Result += 1; } else if (s1P2 > s1P1) { p2Result += 1; }
        if (s2P1 > s2P2) { p1Result += 1; } else if (s2P2 > s2P1) { p2Result += 1; }
        if (s3P1 > s3P2) { p1Result += 1; } else if (s3P2 > s3P1) { p2Result += 1; }

        return p1Result + ":" + p2Result;
    }

    private void setResult (MatchResult result) {
        String setResultStr = setResult(
                result.getPlayer1FirstSet(), result.getPlayer2FirstSet(),
                result.getPlayer1SecondSet(), result.getPlayer2SecondSet(),
                result.getPlayer1ThirdSet(), result.getPlayer2ThirdSet());

        result.setResult(setResultStr);
        result.setPlayer1Sets(Integer.valueOf(setResultStr.split(":")[0]));
        result.setPlayer2Sets(Integer.valueOf(setResultStr.split(":")[1]));
    }

    public void sendResultsToMainController() {
        ArrayList<MatchResult> results = new ArrayList<>();
        for (MatchResult result : tableViewMatches.getItems()) {
            results.add(result);
        }
        parentController.getCurrentTournament().setResults(results);
    }

    private void commitChanges(MatchResult result) {
        setResult(result);
        findWinner(result);
        fillMatches();
        sendResultsToMainController();
    }

    private void findWinner(MatchResult result) {
        if (result.getPlayer1Sets() > result.getPlayer2Sets()) {
            result.setWinner(result.getPlayer1());
            result.setLoser(result.getPlayer2());
        }
        else if (result.getPlayer1Sets() < result.getPlayer2Sets()) {
            result.setWinner(result.getPlayer2());
            result.setLoser(result.getPlayer1());
        }
    }

}
