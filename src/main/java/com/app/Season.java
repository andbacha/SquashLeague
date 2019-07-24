package com.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Season {

    // start date of the season
    LocalDate startDate;
    // list of tournaments included in season
    ArrayList<Tournament> tournaments;
    // list of players
    HashMap<String, Player> players;

    public class Rules {
        // target point value (which ends the season)
        int targetPoints;
    }

}
