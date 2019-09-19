package com.app;

import java.util.ArrayList;

public class MatchResult {

    String player1;
    String player2;
    String result;

    String winner;
    String loser;

    String set1;
    String set2;
    String set3;

    int player1Sets = 0;
    int player2Sets = 0;

    int player1FirstSet = 0;
    int player1SecondSet = 0;
    int player1ThirdSet = 0;
    int player2FirstSet = 0;
    int player2SecondSet = 0;
    int player2ThirdSet = 0;

    public MatchResult() {
        this.result = player1Sets + ":" + player2Sets;
        this.set1 = player1FirstSet + ":" + player2FirstSet;
        this.set2 = player1SecondSet + ":" + player2SecondSet;
        this.set3 = player1ThirdSet + ":" + player2ThirdSet;
    }

    public MatchResult(int player1Sets, int player2Sets) {
        this.player1Sets = player1Sets;
        this.player2Sets = player2Sets;
    }

    public MatchResult(int player1FirstSet, int player1SecondSet, int player2FirstSet, int player2SecondSet) {
        this.player1FirstSet = player1FirstSet;
        this.player1SecondSet = player1SecondSet;
        this.player2FirstSet = player2FirstSet;
        this.player2SecondSet = player2SecondSet;
    }

    public MatchResult(int player1FirstSet, int player1SecondSet, int player1ThirdSet, int player2FirstSet, int player2SecondSet, int player2ThirdSet) {
        this.player1FirstSet = player1FirstSet;
        this.player1SecondSet = player1SecondSet;
        this.player1ThirdSet = player1ThirdSet;
        this.player2FirstSet = player2FirstSet;
        this.player2SecondSet = player2SecondSet;
        this.player2ThirdSet = player2ThirdSet;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSet1() {
        return set1;
    }

    public void setSet1(String set1) {
        this.set1 = set1;
    }

    public String getSet2() {
        return set2;
    }

    public void setSet2(String set2) {
        this.set2 = set2;
    }

    public String getSet3() {
        return set3;
    }

    public void setSet3(String set3) {
        this.set3 = set3;
    }

    public int getPlayer1Sets() {
        return player1Sets;
    }

    public void setPlayer1Sets(int player1Sets) {
        this.player1Sets = player1Sets;
    }

    public int getPlayer2Sets() {
        return player2Sets;
    }

    public void setPlayer2Sets(int player2Sets) {
        this.player2Sets = player2Sets;
    }

    public int getPlayer1FirstSet() {
        return player1FirstSet;
    }

    public void setPlayer1FirstSet(int player1FirstSet) {
        this.player1FirstSet = player1FirstSet;
    }

    public int getPlayer1SecondSet() {
        return player1SecondSet;
    }

    public void setPlayer1SecondSet(int player1SecondSet) {
        this.player1SecondSet = player1SecondSet;
    }

    public int getPlayer1ThirdSet() {
        return player1ThirdSet;
    }

    public void setPlayer1ThirdSet(int player1ThirdSet) {
        this.player1ThirdSet = player1ThirdSet;
    }

    public int getPlayer2FirstSet() {
        return player2FirstSet;
    }

    public void setPlayer2FirstSet(int player2FirstSet) {
        this.player2FirstSet = player2FirstSet;
    }

    public int getPlayer2SecondSet() {
        return player2SecondSet;
    }

    public void setPlayer2SecondSet(int player2SecondSet) {
        this.player2SecondSet = player2SecondSet;
    }

    public int getPlayer2ThirdSet() {
        return player2ThirdSet;
    }

    public void setPlayer2ThirdSet(int player2ThirdSet) {
        this.player2ThirdSet = player2ThirdSet;
    }

    public static ArrayList<MatchResult> generateEmptyResultsArrayList(ArrayList<Player> players) {
        ArrayList<MatchResult> array = new ArrayList<>();
        for (Player player : players) {
            array.add(new MatchResult());
        }
        return array;
    }
}
