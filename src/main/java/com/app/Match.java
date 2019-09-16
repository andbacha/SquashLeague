package com.app;

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
}
