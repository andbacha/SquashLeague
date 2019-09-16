package com.app;

import java.time.LocalDate;
import java.util.HashMap;

public class Tournament {

    // start date of the tournament
    private LocalDate startDate;
    // player list
    private HashMap<String, Player> players;

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
