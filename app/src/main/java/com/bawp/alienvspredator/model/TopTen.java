package com.bawp.alienvspredator.model;

import com.bawp.alienvspredator.util.MySP;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TopTen {

    private ArrayList<Score> topTenScores;
    private final int ARRAY_MAX_SIZE = 10;
    private static TopTen instance;

    private TopTen(){
        topTenScores = new ArrayList<>();
    }

    public static TopTen initHelper(){
        if (instance == null)
            instance = new TopTen();
        return instance;
    }

    public static TopTen getInstance(){
        return instance;
    }
    public ArrayList<Score> getTopTenScores() {
        return topTenScores;
    }

    public void getScoresFromSP(){
        Gson gson = new Gson();
        String json = MySP.getInstance().getString(MySP.KEYS.SCORE_ARRAY, null);
        if (json != null){
            Type type = new TypeToken<List<Score>>() {}.getType();
            topTenScores = gson.fromJson(json, type);
        }
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