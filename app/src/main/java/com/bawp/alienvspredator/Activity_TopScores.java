package com.bawp.alienvspredator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Activity_TopScores extends AppCompatActivity implements OnMapReadyCallback{


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
        Fragment_Map fragment_map = Fragment_Map.newInstance();
        FragmentTransaction transactionMap = getSupportFragmentManager().beginTransaction();
        transactionMap.replace(R.id.TopScores_LAY_map, fragment_map);
        transactionMap.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}