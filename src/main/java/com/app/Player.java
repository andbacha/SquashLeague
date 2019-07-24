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

}
