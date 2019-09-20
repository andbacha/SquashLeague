package com.app;

import java.util.Comparator;

public class Player {

    // PRIVATE FIELDS

    private String playerName;

    // season stats

    private int seasonPlace = 1;
    private int seasonPoints = 0;

    // tournament stats

    private int tournamentPlace = 1;
    private int played = 0;
    private int winLoseBalance = 0;
    private int wins = 0;
    private int loses = 0;
    private int setBalance = 0;
    private int wonSets = 0;
    private int lostSets = 0;
    private int pointBalance = 0;
    private int wonPoints = 0;
    private int lostPoints = 0;

    public static Comparator<Player> comparator = Comparator
            .comparing(Player::getWinLoseBalance)
            .thenComparing(Player::getWins);

    // CONSTRUCTORS

    public Player(String playerName) {
        this.playerName = playerName;
    }

    // GETTERS AND SETTERS

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getSeasonPlace() {
        return seasonPlace;
    }

    public void setSeasonPlace(int seasonPlace) {
        this.seasonPlace = seasonPlace;
    }

    public int getSeasonPoints() {
        return seasonPoints;
    }

    public void setSeasonPoints(int seasonPoints) {
        this.seasonPoints = seasonPoints;
    }

    public int getTournamentPlace() {
        return tournamentPlace;
    }

    public void setTournamentPlace(int tournamentPlace) {
        this.tournamentPlace = tournamentPlace;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWinLoseBalance() {
        return winLoseBalance;
    }

    public void setWinLoseBalance(int winLoseBalance) {
        this.winLoseBalance = winLoseBalance;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getSetBalance() {
        return setBalance;
    }

    public void setSetBalance(int setBalance) {
        this.setBalance = setBalance;
    }

    public int getWonSets() {
        return wonSets;
    }

    public void setWonSets(int wonSets) {
        this.wonSets = wonSets;
    }

    public int getLostSets() {
        return lostSets;
    }

    public void setLostSets(int lostSets) {
        this.lostSets = lostSets;
    }

    public int getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(int pointBalance) {
        this.pointBalance = pointBalance;
    }

    public int getWonPoints() {
        return wonPoints;
    }

    public void setWonPoints(int wonPoints) {
        this.wonPoints = wonPoints;
    }

    public int getLostPoints() {
        return lostPoints;
    }

    public void setLostPoints(int lostPoints) {
        this.lostPoints = lostPoints;
    }
}
