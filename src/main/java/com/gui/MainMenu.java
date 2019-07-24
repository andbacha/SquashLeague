package com.gui;

import com.app.Season;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;

public class MainMenu {

    @FXML
    private MenuItem menuNewSeason;

    @FXML
    private MenuItem menuLoadSeason;

    @FXML
    private MenuItem menuSaveSeason;

    @FXML
    private MenuItem menuExportPlayers;

    @FXML
    private MenuItem menuImportPlayers;

    @FXML
    private MenuItem menuClose;

    @FXML
    private MenuItem menuAddTournament;

    @FXML
    private MenuItem menuAddPlayer;

    @FXML
    private MenuItem menuModifyTournament;

    @FXML
    private MenuItem menuModifyPlayer;

    @FXML
    private MenuItem menuRemoveTournament;

    @FXML
    private MenuItem menuRemovePlayer;

    @FXML
    private MenuItem menuAbout;

    @FXML
    private Button buttonNewSeason;

    @FXML
    private Button buttonNewTournament;

    @FXML
    private Button buttonRemoveTournament;

    @FXML
    private Button buttonImportPlayers;

    @FXML
    private Button buttonExportPlayers;

    @FXML
    private Label labelBottom;

    @FXML
    private Hyperlink hyperlinkMatches;

    @FXML
    private Hyperlink hyperlinkTournamentTable;

    @FXML
    private Hyperlink hyperlinkPlayers;

    @FXML
    private Hyperlink hyperlinkSeasonTable;

    @FXML
    private Hyperlink hyperlinkTournamentRules;

    @FXML
    private Hyperlink hyperlinkSeasonRules;

    @FXML
    private Hyperlink hyperlinkEndTournament;

    @FXML
    private Hyperlink hyperlinkEndSeason;

    @FXML
    private AnchorPane anchorPaneCenterContent;

    // hyperlinks HashMap for hyperlink <> fxml mapping
    HashMap<String, String> hyperlinks = FXMLMapping.getHyperlinks();

    // PUBLIC METHODS

    // set central content of MainMenu window
    public void setCenterContent(ActionEvent actionEvent) throws Exception {
        Hyperlink hyperlink = (Hyperlink) actionEvent.getSource();
//        HashMap<String, String> hyperlinks = FXMLMapping.getHyperlinks();
        Pane loadedPane = FXMLLoader.load(getClass().getResource(hyperlinks.get(hyperlink.getId())));
        anchorPaneCenterContent.getChildren().clear();
        anchorPaneCenterContent.getChildren().add(loadedPane);
        AnchorPane.setTopAnchor(loadedPane, 0.0);
        AnchorPane.setBottomAnchor(loadedPane, 0.0);
        AnchorPane.setLeftAnchor(loadedPane, 0.0);
        AnchorPane.setRightAnchor(loadedPane, 0.0);
    }

    // opens new dialog with given title and fxml file layout
    public void openDialog(String title, String fxmlFile) throws Exception {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        window.setScene(new Scene(root));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);
        window.show();
    }

    // displays warning dialog window
    public void displayWarningDialog(ActionEvent actionEvent) throws Exception {
        Hyperlink hyperlink = (Hyperlink) actionEvent.getSource();
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(hyperlinks.get(hyperlink.getId())));
        window.setScene(new Scene(root));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Potwierdź");
        window.setResizable(false);
        window.show();
    }

    // set all hyperlinks on the left enabled (true) or disabled (false)
    public void setGlobalHyperlinksEnabled(boolean state) {
        hyperlinkMatches.setDisable(!state);
        hyperlinkTournamentTable.setDisable(!state);
        hyperlinkPlayers.setDisable(!state);
        hyperlinkSeasonTable.setDisable(!state);
        hyperlinkTournamentRules.setDisable(!state);
        hyperlinkSeasonRules.setDisable(!state);
        hyperlinkEndTournament.setDisable(!state);
        hyperlinkEndSeason.setDisable(!state);
    }

    // handler for "New Season" action
    public void handleActionNewSeason(ActionEvent actionEvent) throws Exception {
        openDialog("Nowy sezon", "DialogNewSeason.fxml");

    }

    // handler for "New Tournament" action
    public void handleActionNewTournament(ActionEvent actionEvent) {
        setGlobalHyperlinksEnabled(true);
    }
}
