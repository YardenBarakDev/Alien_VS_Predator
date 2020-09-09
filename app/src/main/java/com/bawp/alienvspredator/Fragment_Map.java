package com.bawp.alienvspredator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {







    protected View view;
    private GoogleMap map;
    SupportMapFragment supportMapFragment;
    public Fragment_Map() {
        // Required empty public constructor
    }

    public static Fragment_Map newInstance() {
        return new Fragment_Map();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.TopScores_map_API);
        mapFragment.getMapAsync(this);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if(getActivity()!=null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.TopScores_map_API);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        for (int i = 0; i <  TopTen.getInstance().getTopTenScores().size(); i++) {
            LatLng pp = new LatLng( TopTen.getInstance().getTopTenScores().get(i).getLat(),
                    TopTen.getInstance().getTopTenScores().get(i).getLon());
            String title = (i+1) + "th place";
            map.addMarker(new MarkerOptions().position(pp).title(title));

        }
        //LatLng pp = new LatLng(-34,151);
       // map.addMarker(new MarkerOptions().position(pp).title("Sydney"));

    }
}
