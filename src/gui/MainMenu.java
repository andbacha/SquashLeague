package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;

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
    private Pane paneCenterContent;

    public void setCenterContent(ActionEvent actionEvent) throws Exception {
        Hyperlink hyperlink = (Hyperlink) actionEvent.getSource();
        HashMap<String, String> hyperlinks = Hyperlinks.getHyperlinks();
        Pane loadedPane = FXMLLoader.load(getClass().getResource(hyperlinks.get(hyperlink.getId())));
        paneCenterContent.getChildren().clear();
        paneCenterContent.getChildren().add(loadedPane);
    }

}
