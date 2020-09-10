package com.bawp.alienvspredator;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

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


    //this callback get the lan and lon from the ListView and make the map zoom in to the location
    CallBack_ListToMap callBack_listToMap = new CallBack_ListToMap() {
        @Override
        public void reachLocation(double lan, double lon) {
            LatLng latLng = new LatLng(lan, lon);
            fragment_map.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }
    };

}