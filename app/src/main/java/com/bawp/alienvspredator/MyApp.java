package com.bawp.alienvspredator;

import android.app.Application;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        MySP.initHelper(this);
        TopTen.initHelper();
        TopTen.getInstance().getScoresFromSP();
    }

}
