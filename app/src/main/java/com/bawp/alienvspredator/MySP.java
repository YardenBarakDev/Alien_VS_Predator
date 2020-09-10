package com.bawp.alienvspredator;

import android.content.Context;
import android.content.SharedPreferences;

public class MySP {

    public interface KEYS{
        String WINNER = "WINNER";
        String ROUNDS = "ROUNDS";
        String SCORE_ARRAY = "SCORE_ARRAY";
    }

    private static MySP instance;
    private SharedPreferences prefs;

    public static MySP getInstance(){
        return instance;
    }

    private MySP(Context context){
        prefs = context.getApplicationContext().getSharedPreferences("APP_DB", Context.MODE_PRIVATE);
    }

    public static MySP initHelper(Context context){
        if (instance == null)
            instance = new MySP(context);
        return instance;
    }

    //***************String*************************
    public void putString(String key, String value){
        prefs.edit().putString(key, value).apply();
    }

    public String getString(String key, String def){
        return prefs.getString(key, def);
    }


    //***************Boolean*************************
    public void putBoolean(String key, Boolean value){
        prefs.edit().putBoolean(key, value).apply();
    }
    public Boolean getBoolean(String key, Boolean def){
        return prefs.getBoolean(key, def);
    }


    //***************int*************************
    public void putInt(String key, int value){
        prefs.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int def){
        return prefs.getInt(key, def);
    }
}
