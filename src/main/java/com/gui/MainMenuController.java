package com.gui;

import com.app.Season;
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
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    // FIELDS - LOGIC

    boolean isSeasonCreated;

    Season currentSeason;

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

    public void setCenterContentPlayers() throws IOException {
        FXMLLoader loader = setCenterContent("ContentPlayers.fxml");

        // Provide DialogNewSeasonController object to child controller
        ContentPlayers childController = loader.getController();
        childController.setParentController(this);

        // Fill players' list
        childController.fillPlayers(currentSeason.getPlayers().keySet());
    }

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
    public void openModalDialog(String title, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        Scene modalDialogScene = new Scene(root);

        // Provide MainMenuController object to child controller
        DialogNewSeasonController childController = loader.getController();
        childController.setParentController(this);

        // Set and display stage
        Stage modalDialog = new Stage();
        modalDialog.setScene(modalDialogScene);
        modalDialog.initModality(Modality.APPLICATION_MODAL);
        modalDialog.setTitle(title);
        modalDialog.setResizable(false);
        modalDialog.showAndWait();
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
        menuExportPlayers.setDisable(state);
        menuImportPlayers.setDisable(state);

        // menu "Dodaj"
        menuAddTournament.setDisable(state);
        menuAddPlayer.setDisable(state);

        // menu "Edytuj"
        menuModifyTournament.setDisable(state);
        menuModifyPlayer.setDisable(state);
        menuRemoveTournament.setDisable(state);
        menuRemovePlayer.setDisable(state);

        // toolbar buttons
        buttonNewTournament.setDisable(state);
        buttonRemoveTournament.setDisable(state);
        buttonImportPlayers.setDisable(state);
        buttonExportPlayers.setDisable(state);
    }

    /**
     * Sets menu items' and toolbar buttons' states depending on season state (opened or not).
     * @param state state of items - disabled if true
     */
    public void setMenuItemStatesDependingOnSeason(boolean state) {
        // menu "Plik"
        menuSaveSeason.setDisable(state);
        menuExportPlayers.setDisable(state);
        menuImportPlayers.setDisable(state);

        // menu "Dodaj"
        menuAddTournament.setDisable(state);
        menuAddPlayer.setDisable(state);

        // menu "Edytuj"
        menuModifyPlayer.setDisable(state);
        menuRemovePlayer.setDisable(state);

        // toolbar buttons
        buttonNewTournament.setDisable(state);
        buttonImportPlayers.setDisable(state);
        buttonExportPlayers.setDisable(state);
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
        openModalDialog("Nowy sezon", "DialogNewSeason.fxml");
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
//        openModalDialog("Nowy turniej", "DialogNewTournament.fxml");
        setHyperlinkStates(true);
        disableEnableMenuItems(false);
    }

}
