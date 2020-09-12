package com.bawp.alienvspredator.model;


public class Score implements Comparable<Score>{
    private boolean winner;
    private int numOfMoves;
    private double lat;
    private double lon;
    private String timeStamp;

    public Score(){

    }

    public Score(boolean winner, int numOfMoves, double lat, double lon, String timeStamp) {
        this.winner = winner;
        this.numOfMoves = numOfMoves;
        this.lat = lat;
        this.lon = lon;
        this.timeStamp = timeStamp;
    }

    public boolean getWinner() {
        return winner;
    }

    public Score setWinner(boolean winner) {
        this.winner = winner;
        return this;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public Score setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Score setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Score setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Score setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }


    @Override
    public int compareTo(Score score) {
        return Integer.compare(this.getNumOfMoves(), score.getNumOfMoves());
    }
}
