package com.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Tournament {

    // start date of the tournament
    private LocalDate startDate;
    // player list
    private ArrayList<Player> players;

    private ArrayList<Match> matches;

    private ArrayList<MatchResult> results;

    public LocalDate getStartDate() {
        return startDate;
    }

    // use calendar picker
    public void setStartDate() {
        this.startDate = LocalDate.now();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public ArrayList<MatchResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<MatchResult> results) {
        this.results = results;
    }

    public static class PointsPerStanding {
        private int standing;
        private int pointsPerStanding;
        private String pointsPerStandingString = "0";

        public PointsPerStanding(int standing, String pointsPerStandingString) {
            this.standing = standing;
            this.pointsPerStandingString = pointsPerStandingString;
        }

        public int getStanding() {
            return standing;
        }

        public void setStanding(int standing) {
            this.standing = standing;
        }

        public int getPointsPerStanding() {
            return pointsPerStanding;
        }

        public void setPointsPerStanding(int pointsPerStanding) {
            this.pointsPerStanding = pointsPerStanding;
        }

        public String getPointsPerStandingString() {
            return pointsPerStandingString;
        }



        public void setPointsPerStandingString(String pointsPerStandingString) {
            this.pointsPerStandingString = pointsPerStandingString;
        }
    }
}
