package com.bawp.alienvspredator.util;

import android.app.Application;

import com.bawp.alienvspredator.model.TopTen;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        MySP.initHelper(this);
        TopTen.initHelper();
        //fetch the top ten scores to ArrayList<Score> topTenScores;
        TopTen.getInstance().getScoresFromSP();
    }

}
