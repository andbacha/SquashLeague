package gui;

import java.util.HashMap;

public abstract class Hyperlinks {

    static HashMap<String, String> hyperlinks = new HashMap<>();

    public static HashMap<String, String> getHyperlinks() {
        hyperlinks.put("hyperlinkMatches", "ContentMatches.fxml");
        hyperlinks.put("hyperlinkTournamentTable", "ContentTournamentTable.fxml");
        hyperlinks.put("hyperlinkPlayers", "ContentPlayers.fxml");
        hyperlinks.put("hyperlinkSeasonTable", "ContentSeasonTable.fxml");
        hyperlinks.put("hyperlinkTournamentRules", "ContentTournamentRules.fxml");
        hyperlinks.put("hyperlinkSeasonRules", "ContentSeasonRules.fxml");
        hyperlinks.put("hyperlinkEndTournament", "ContentEndTournament.fxml");
        hyperlinks.put("hyperlinkEndSeason", "ContentEndSeason.fxml");
        return hyperlinks;
    }
}
