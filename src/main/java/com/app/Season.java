package com.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Season {

    // PRIVATE FIELDS

    // start date of the season
    private LocalDate startDate;
    // list of tournaments included in season
    private ArrayList<Tournament> tournaments;
    // list of players
    private HashMap<String, Player> players;

    public class Rules {
        // target point value (which ends the season)
        private int targetPoints;
    }

    // CONSTRUCTORS

    public Season() {

    }

    public Season(LocalDate startDate, HashMap<String, Player> players) {
        this.startDate = startDate;
        this.players = players;
    }

    // GET / SET METHODS

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(ArrayList<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }
}
