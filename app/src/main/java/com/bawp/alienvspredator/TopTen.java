package com.bawp.alienvspredator;

public class TopTen {
    double lat;
    double lon;
    long timeStamp;
    int numOfMoves;
    boolean winner;

    public TopTen() {

    }

    public TopTen(double lat, double lon, long timeStamp, int numOfMoves, boolean winner) {
        this.lat = lat;
        this.lon = lon;
        this.timeStamp = timeStamp;
        this.numOfMoves = numOfMoves;
        this.winner = winner;
    }

    public double getLat() {
        return lat;
    }

    public TopTen setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public TopTen setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public TopTen setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public TopTen setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
        return this;
    }

    public boolean isWinner() {
        return winner;
    }

    public TopTen setWinner(boolean winner) {
        this.winner = winner;
        return this;
    }
}