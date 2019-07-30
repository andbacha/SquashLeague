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

public class MainMenuController implements Initializable {

    boolean isSeasonCreated;

    // GUI COMPONENTS

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMenuItemsStateWhenSeasonClosed();
        disableHyperlinks();
    }

    // PUBLIC METHODS

    /**
     * Set central content of MainMenuController window. Action is triggered by all of the Hyperlink items placed on the left side of UI.
     * @param actionEvent
     * @throws Exception
     */
    public void setCenterContent(ActionEvent actionEvent) throws Exception {
        Hyperlink hyperlink = (Hyperlink) actionEvent.getSource();
        Pane loadedPane = FXMLLoader.load(getClass().getResource(hyperlinks.get(hyperlink.getId())));
        anchorPaneCenterContent.getChildren().clear();
        anchorPaneCenterContent.getChildren().add(loadedPane);
        AnchorPane.setTopAnchor(loadedPane, 0.0);
        AnchorPane.setBottomAnchor(loadedPane, 0.0);
        AnchorPane.setLeftAnchor(loadedPane, 0.0);
        AnchorPane.setRightAnchor(loadedPane, 0.0);
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
     * Sets menu items' and toolbar buttons' states depending on season state (opened or not).
     * @param state state of items - disabled if true
     */
    public void setMenuItemsStateDependingOnSeason(boolean state) {
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
     * Disables several buttons in situation when season is not opened.
     */
    public void setMenuItemsStateWhenSeasonClosed() {
        setMenuItemsStateDependingOnSeason(true);
    }

    /**
     * Enables several buttons in situation when season is opened.
     */
    public void setMenuItemsStateWhenSeasonOpened() {
        setMenuItemsStateDependingOnSeason(false);
    }

    /**
     * Enables all of the hyperlinks placed on the left side of UI.
     */
    public void enableHyperlinks() {
        hyperlinkMatches.setDisable(false);
        hyperlinkTournamentTable.setDisable(false);
        hyperlinkPlayers.setDisable(false);
        hyperlinkSeasonTable.setDisable(false);
        hyperlinkTournamentRules.setDisable(false);
        hyperlinkSeasonRules.setDisable(false);
        hyperlinkEndTournament.setDisable(false);
        hyperlinkEndSeason.setDisable(false);
    }

    /**
     * Disables all of the hyperlinks placed on the left side of UI.
     */
    public void disableHyperlinks() {
        hyperlinkMatches.setDisable(true);
        hyperlinkTournamentTable.setDisable(true);
        hyperlinkPlayers.setDisable(true);
        hyperlinkSeasonTable.setDisable(true);
        hyperlinkTournamentRules.setDisable(true);
        hyperlinkSeasonRules.setDisable(true);
        hyperlinkEndTournament.setDisable(true);
        hyperlinkEndSeason.setDisable(true);
    }

    /**
     * Button handler for "New Season" action.
     * @param actionEvent
     * @throws Exception
     */
    public void handleActionNewSeason(ActionEvent actionEvent) throws Exception {
        openModalDialog("Nowy sezon", "DialogNewSeason.fxml");
        if (isSeasonCreated) {
            enableHyperlinks();
        }
    }

    /**
     * Button handler for "New Tournament" action.
     * @param actionEvent
     * @throws Exception
     */
    public void handleActionNewTournament(ActionEvent actionEvent) throws Exception {
        openModalDialog("Nowy turniej", "DialogNewTournament.fxml");
    }

}
