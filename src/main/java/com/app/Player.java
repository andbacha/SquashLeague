package com.app;

public class Player {

    // PRIVATE FIELDS

    private String playerName;

    // season stats

    private int seasonPlace = 1;
    private int seasonPoints = 0;

    // tournament stats

    private int tournamentPlace = 1;
    private int tournamentPoints = 0;
    private int wins = 0;
    private int loses = 0;
    private int setBalance = 0;
    private int wonSets = 0;
    private int lostSets = 0;
    private int smallPointBalance = 0;
    private int wonSmallPoints = 0;
    private int lostSmallPoints = 0;

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

    public int getTournamentPoints() {
        return tournamentPoints;
    }

    public void setTournamentPoints(int tournamentPoints) {
        this.tournamentPoints = tournamentPoints;
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

    public int getSmallPointBalance() {
        return smallPointBalance;
    }

    public void setSmallPointBalance(int smallPointBalance) {
        this.smallPointBalance = smallPointBalance;
    }

    public int getWonSmallPoints() {
        return wonSmallPoints;
    }

    public void setWonSmallPoints(int wonSmallPoints) {
        this.wonSmallPoints = wonSmallPoints;
    }

    public int getLostSmallPoints() {
        return lostSmallPoints;
    }

    public void setLostSmallPoints(int lostSmallPoints) {
        this.lostSmallPoints = lostSmallPoints;
    }
}
