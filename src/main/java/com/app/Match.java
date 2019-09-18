package com.app;

import java.util.ArrayList;

public class Match {

    Player player1;
    Player player2;
    MatchResult result;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public static ArrayList<Match> generateMatches(ArrayList<Player> players) {
        ArrayList<Match> matches = new ArrayList<>();
        Match currentMatch;

        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                currentMatch = new Match(players.get(i), players.get(j));
                matches.add(currentMatch);
                currentMatch.getResult().setPlayer1(players.get(i).getPlayerName());
                currentMatch.getResult().setPlayer2(players.get(j).getPlayerName());
            }
        }

        return matches;
    }
}
