package com.bawp.alienvspredator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TopTen {

    private ArrayList<Score> topTenScores;
    private final int ARRAY_MAX_SIZE = 10;

    public TopTen() {
    }

    public ArrayList<Score> getTopTenScores() {
        return topTenScores;
    }

    public TopTen setTopTenScores(ArrayList<Score> topTenScores) {
        this.topTenScores = topTenScores;
        return this;
    }

    public void getScoresFromSP(){
        Gson gson = new Gson();
        String json = MySP.getInstance().getString(MySP.KEYS.SCORE_ARRAY, null);
        Type type = new TypeToken<List<Score>>() {}.getType();
        topTenScores = gson.fromJson(json, type);

    }

    public void saveScoresInSP(){
        Gson gson = new Gson();
        String json = gson.toJson(topTenScores);
        MySP.getInstance().putString(MySP.KEYS.SCORE_ARRAY, json);
    }
    public int getARRAY_MAX_SIZE() {
        return ARRAY_MAX_SIZE;
    }
}