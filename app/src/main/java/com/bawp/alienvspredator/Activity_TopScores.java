package com.bawp.alienvspredator;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Activity_TopScores extends AppCompatActivity {

    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__topscores);

        initFragments();
    }

    private void initFragments() {
        //list
        fragment_list = Fragment_List.newInstance();
        fragment_list.setActivityCallBack(callBack_listToMap);
        FragmentTransaction transactionList = getSupportFragmentManager().beginTransaction();
        transactionList.replace(R.id.TopScores_LAY_list, fragment_list);
        transactionList.commit();

        //map
        fragment_map = Fragment_Map.newInstance();
        FragmentTransaction transactionMap = getSupportFragmentManager().beginTransaction();
        transactionMap.replace(R.id.TopScores_LAY_map, fragment_map);
        transactionMap.commit();
    }

    CallBack_ListToMap callBack_listToMap = new CallBack_ListToMap() {
        @Override
        public void reachLocation(double lan, double lon) {
            Log.d("yarden", "reachLocation: ");
            LatLng latLng = new LatLng(lan, lon);
            fragment_map.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }
    };
}