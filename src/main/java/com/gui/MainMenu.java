package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {

    private Stage modalDialog;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setGlobalHyperlinksEnabled(false);
        setMenuItemsStateWhenSeasonClosed();

    }

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
    public void openModalDialog(String title, String fxmlFile) throws Exception {

        modalDialog = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        modalDialog.setScene(new Scene(root));
        modalDialog.initModality(Modality.APPLICATION_MODAL);
        modalDialog.setTitle(title);
        modalDialog.setResizable(false);
        modalDialog.show();

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

    // sets menu items' state whether any season is opened or not
    public void setMenuItemsStateDependingOnSeason(boolean state) {

        // menu Plik
        menuSaveSeason.setDisable(state);
        menuExportPlayers.setDisable(state);
        menuImportPlayers.setDisable(state);

        // menu Dodaj
        menuAddTournament.setDisable(state);
        menuAddPlayer.setDisable(state);

        // menu Edytuj
        menuModifyTournament.setDisable(state);
        menuModifyPlayer.setDisable(state);
        menuRemoveTournament.setDisable(state);
        menuRemovePlayer.setDisable(state);

        // toolbar
        buttonNewTournament.setDisable(state);
        buttonRemoveTournament.setDisable(state);
        buttonImportPlayers.setDisable(state);
        buttonExportPlayers.setDisable(state);

    }

    public void setMenuItemsStateWhenSeasonClosed() {
        setMenuItemsStateDependingOnSeason(true);
    }

    public void setMenuItemsStateWhenSeasonOpened() {
        setMenuItemsStateDependingOnSeason(false);
    }

    // handler for "New Season" action
    public void handleActionNewSeason(ActionEvent actionEvent) throws Exception {

        openModalDialog("Nowy sezon", "DialogNewSeason.fxml");
        setMenuItemsStateWhenSeasonOpened();

    }

    // handler for "New Tournament" action
    public void handleActionNewTournament(ActionEvent actionEvent) throws Exception {

        openModalDialog("Nowy turniej", "DialogNewTournament.fxml");
        setGlobalHyperlinksEnabled(true);

    }
}
