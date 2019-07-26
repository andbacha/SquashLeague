package com.gui;

import java.util.HashMap;

public abstract class FXMLMapping {

    static HashMap<String, String> hyperlinks = new HashMap<>();
    static HashMap<String, String> buttons = new HashMap<>();
    static HashMap<String, String> menuItems = new HashMap<>();

    public static HashMap<String, String> getHyperlinks() {
        hyperlinks.put("hyperlinkMatches", "ContentMatches.fxml");
        hyperlinks.put("hyperlinkTournamentTable", "ContentTournamentTable.fxml");
        hyperlinks.put("hyperlinkPlayers", "ContentPlayers.fxml");
        hyperlinks.put("hyperlinkSeasonTable", "ContentSeasonTable.fxml");
        hyperlinks.put("hyperlinkTournamentRules", "ContentTournamentRules.fxml");
        hyperlinks.put("hyperlinkSeasonRules", "ContentSeasonRules.fxml");
        return hyperlinks;
    }

}
