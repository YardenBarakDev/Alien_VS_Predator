package com.bawp.alienvspredator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Activity_TopScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__topscores);

        initFragments();
    }

    private void initFragments() {
        //list
        Fragment_List fragment_list = Fragment_List.newInstance();
        FragmentTransaction transactionList = getSupportFragmentManager().beginTransaction();
        transactionList.replace(R.id.TopScores_LAY_list, fragment_list);
        transactionList.commit();

        //map

    }
}