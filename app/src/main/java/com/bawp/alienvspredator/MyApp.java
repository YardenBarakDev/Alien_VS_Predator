package com.bawp.alienvspredator;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static class Score {
        private String name;
        private int rounds;

    }
}
