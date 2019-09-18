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

import javax.xml.transform.Result;
import java.net.URL;
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

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnPlayer1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        tableColumnPlayer2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        tableColumnResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        tableColumnSet1.setCellValueFactory(new PropertyValueFactory<>("set1"));
        tableColumnSet2.setCellValueFactory(new PropertyValueFactory<>("set2"));
        tableColumnSet3.setCellValueFactory(new PropertyValueFactory<>("set3"));

        tableColumnPlayer1.setStyle("-fx-alignment: CENTER-RIGHT");
        tableColumnResult.setStyle("-fx-alignment: CENTER");
        tableColumnPlayer2.setStyle("-fx-alignment: CENTER-LEFT");
        tableColumnSet1.setStyle("-fx-alignment: CENTER");
        tableColumnSet2.setStyle("-fx-alignment: CENTER");
        tableColumnSet3.setStyle("-fx-alignment: CENTER");
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
        tableViewMatches.getColumns().addAll(tableColumnPlayer1, tableColumnResult, tableColumnPlayer2, tableColumnSet1, tableColumnSet2, tableColumnSet3);
    }

    public MainMenuController getParentController() {
        return parentController;
    }

    public void setParentController(MainMenuController parentcontroller) {
        this.parentController = parentcontroller;
    }
}
