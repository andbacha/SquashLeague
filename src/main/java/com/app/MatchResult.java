package com.app;

public class MatchResult {

    String player1;
    String player2;

    int player1Sets;
    int player2Sets;

    int player1FirstSet;
    int player1SecondSet;
    int player1ThirdSet;

    int player2FirstSet;
    int player2SecondSet;
    int player2ThirdSet;

    public MatchResult() {}

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
}
