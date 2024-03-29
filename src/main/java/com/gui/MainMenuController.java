package com.gui;

import com.app.Match;
import com.app.MatchResult;
import com.app.Season;
import com.app.Tournament;
import com.utils.SeasonXmlParser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    // FIELDS - LOGIC

    boolean isSeasonCreated;

    Season currentSeason = new Season();
    int seasonEndPoints;
    Tournament currentTournament = new Tournament();
    Tournament.PointsPerStanding tournamentRules;

// GUI COMPONENTS

    @FXML
    private AnchorPane paneCenterContent;

    private Pane paneChildCenterContent;

    @FXML
    private MenuItem menuNewSeason;

    @FXML
    private MenuItem menuLoadSeason;

    @FXML
    private MenuItem menuSaveSeason;

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
    private MenuItem menuAbout;

    @FXML
    private Button buttonNewSeason;

    @FXML
    private Button buttonNewTournament;

    @FXML
    private Button buttonRemoveTournament;

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

    // modal dialog
    Stage modalDialog;

    /**
     * Hyperlinks HashMap for Hyperlink <-> destination Scene FXML mapping.
     */
    HashMap<String, String> hyperlinks = FXMLMapping.getHyperlinks();

    public boolean isSeasonCreated() {
        return isSeasonCreated;
    }

    public void setSeasonCreated(boolean seasonCreated) {
        isSeasonCreated = seasonCreated;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }

    public Tournament getCurrentTournament() {
        return currentTournament;
    }

    public void setCurrentTournament(Tournament currentTournament) {
        this.currentTournament = currentTournament;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableMenuItemsWhenSeasonNotOpened();
        setHyperlinkStates(false);
    }

    // PUBLIC METHODS

    /**
     * Set central content of main menu window.
     * @param fxmlFile path to FXML file of layout
     */
    public FXMLLoader setCenterContent(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        paneCenterContent.getChildren().clear();
        AnchorPane.setTopAnchor(root, 0d);
        AnchorPane.setBottomAnchor(root, 0d);
        AnchorPane.setLeftAnchor(root, 0d);
        AnchorPane.setRightAnchor(root, 0d);
        paneCenterContent.getChildren().add(root);

        return loader;
    }

    public ContentPlayersController setCenterContentPlayers() throws IOException {
        ContentPlayersController childController = setCenterContent("ContentPlayers.fxml").getController();
        childController.setParentController(this);

        // Fill playerNames' list
        childController.setPlayers(currentSeason.getPlayers().keySet());
        childController.fillPlayers(childController.getPlayers());
        return childController;
    }

    public void setCenterContentSeasonTable() throws IOException {
        FXMLLoader loader = setCenterContent("ContentSeasonTable.fxml");

        // Provide DialogNewSeasonController object to child controller
        ContentSeasonTable childController = loader.getController();
        childController.setParentController(this);

        // Fill season table
        childController.setPlayers(currentSeason.getPlayers());
        childController.fillSeasonTable();
    }

    public void setCenterContentTournamentTable() throws IOException {
        ContentTournamentTable childController = setCenterContent("ContentTournamentTable.fxml").getController();
        childController.setParentController(this);

        // Fill playerNames' list
        childController.fillTournamentTable();
    }

    public void setCenterContentMatches() throws IOException {
        ContentMatchesController childController = setCenterContent("ContentMatches.fxml").getController();
        childController.setParentController(this);

        // Fill playerNames' list
        childController.fillMatches();
    }

    @Deprecated
    /**
     * Set central content of main menu - action is triggered by all of the Hyperlink items placed on the left side of UI.
     * @param actionEvent
     * @throws Exception
     */
    public void hyperlinkClick(ActionEvent actionEvent) throws Exception {
        Hyperlink hyperlink = (Hyperlink) actionEvent.getSource();
        setCenterContent(hyperlinks.get(hyperlink.getId()));
    }

    /**
     * Opens new modal dialog.
     * @param title title of opened modal dialog
     * @param fxmlFile path to FXML file for scene template
     * @throws Exception
     */
    public FXMLLoader openModalDialog(String title, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        Scene modalDialogScene = new Scene(root);

        // Set and display stage
        modalDialog = new Stage();
        modalDialog.setScene(modalDialogScene);
        modalDialog.initModality(Modality.APPLICATION_MODAL);
        modalDialog.setTitle(title);
        modalDialog.setResizable(false);
        return loader;
    }

    /**
     * Displays warning dialog window.
     * @param actionEvent
     * @throws Exception
     */
    public void displayWarningDialog(ActionEvent actionEvent) throws Exception {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("WarningDialog.fxml"));
        window.setScene(new Scene(root));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Potwierdź");
        window.setResizable(false);
        window.showAndWait();
    }

    /**
     * Enables or disables all menu items
     * @param state state of items - disabled if true
     */
    public void disableEnableMenuItems(boolean state) {
        // menu "Plik"
        menuSaveSeason.setDisable(state);

        // menu "Dodaj"
        menuAddTournament.setDisable(state);
        menuAddPlayer.setDisable(state);

        // menu "Edytuj"
        menuModifyTournament.setDisable(state);
        menuModifyPlayer.setDisable(state);

        // toolbar buttons
        buttonNewTournament.setDisable(state);
        buttonRemoveTournament.setDisable(state);
    }

    /**
     * Sets menu items' and toolbar buttons' states depending on season state (opened or not).
     * @param state state of items - disabled if true
     */
    public void setMenuItemStatesDependingOnSeason(boolean state) {
        // menu "Plik"
        menuSaveSeason.setDisable(state);

        // menu "Dodaj"
        menuAddTournament.setDisable(state);
        menuAddPlayer.setDisable(state);

        // menu "Edytuj"
        menuModifyPlayer.setDisable(state);
        menuModifyTournament.setDisable(state);

        // toolbar buttons
        buttonNewTournament.setDisable(state);
        buttonRemoveTournament.setDisable(state);
    }

    /**
     * Disables several buttons in situation when season is not opened.
     */
    public void disableMenuItemsWhenSeasonNotOpened() {
        setMenuItemStatesDependingOnSeason(true);
    }

    /**
     * Enables several buttons in situation when season is opened.
     */
    public void enableMenuItemsWhenSeasonOpened() {
        setMenuItemStatesDependingOnSeason(false);
    }

    /**
     * Sets state of all hyperlinks on the left side of main menu
     * @param state true (enabled), false (disabled)
     */
    public void setHyperlinkStates(boolean state) {
        hyperlinkMatches.setDisable(!state);
        hyperlinkTournamentTable.setDisable(!state);
        hyperlinkPlayers.setDisable(!state);
        hyperlinkSeasonTable.setDisable(!state);
        hyperlinkTournamentRules.setDisable(!state);
        hyperlinkSeasonRules.setDisable(!state);
        hyperlinkEndTournament.setDisable(!state);
        hyperlinkEndSeason.setDisable(!state);
    }

    /**
     * Sets proper state of hyperlinks on the left side of main menu when season is opened or not
     * @param state true (season opened), false (season closed)
     */
    public void setHyperlinkStatesWhenSeasonOpened(boolean state) {
        hyperlinkPlayers.setDisable(!state);
        hyperlinkSeasonTable.setDisable(!state);
        hyperlinkSeasonRules.setDisable(!state);
        hyperlinkEndSeason.setDisable(!state);
    }

    /**
     * Button handler for "New Season" action.
     * @param actionEvent
     * @throws Exception
     */
    public void handleActionNewSeason(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = openModalDialog("Nowy sezon", "DialogNewSeason.fxml");
        // Provide MainMenuController object to child controller
        DialogNewSeasonController childController = loader.getController();
        childController.setParentController(this);
        modalDialog.showAndWait();

        if (isSeasonCreated) {
            setHyperlinkStatesWhenSeasonOpened(true);
            enableMenuItemsWhenSeasonOpened();
            setCenterContentPlayers();
        }
    }

    /**
     * Button handler for "New Tournament" action.
     * @param actionEvent
     * @throws Exception
     */
    public void handleActionNewTournament(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = openModalDialog("Nowy turniej", "DialogNewTournament.fxml");
        // Provide MainMenuController object to child controller
        DialogNewTournament childController = loader.getController();
        childController.fillSeasonPlayers(currentSeason.getPlayers().keySet());
        childController.setParentController(this);
        modalDialog.showAndWait();
        setHyperlinkStates(true);
        disableEnableMenuItems(false);
        currentTournament.setMatches(Match.generateMatches(currentTournament.getPlayers()));
        currentTournament.setResults(MatchResult.generateEmptyResultsArrayList(currentTournament.getPlayers()));
        setCenterContentTournamentTable();
    }

    public void loadSeason(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik sezonu");
        File seasonFile = fileChooser.showOpenDialog(null);
        if (seasonFile != null && seasonFile.exists()) {
            currentSeason = SeasonXmlParser.seasonParser(seasonFile);
            setHyperlinkStatesWhenSeasonOpened(true);
            enableMenuItemsWhenSeasonOpened();
            setCenterContentPlayers();
        }
    }

    public void closeApplication(ActionEvent actionEvent) {
        // close window
        Platform.exit();
    }

    public void addPlayer(ActionEvent actionEvent) throws IOException {
        setCenterContentPlayers().addPlayer(actionEvent);
    }

    public void about(ActionEvent actionEvent) throws Exception {
        openModalDialog("O programie", "InfoDialog.fxml");
        modalDialog.show();
    }

    public void endTournament(ActionEvent actionEvent) throws Exception{
//        displayWarningDialog(actionEvent);
        currentSeason.getTournaments().add(currentTournament);
        setCenterContentSeasonTable();
        disableEnableMenuItems(true);
        enableMenuItemsWhenSeasonOpened();
        setHyperlinkStates(false);
        setHyperlinkStatesWhenSeasonOpened(true);
        currentTournament = new Tournament();
    }
}
