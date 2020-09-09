package com.bawp.alienvspredator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Collections;
import java.util.Comparator;


public class Fragment_List extends Fragment implements AdapterView.OnItemClickListener{


    public interface ToMapListener{
        void reachLocation(double lan, double lon);
    }

    private ToMapListener toMapListener;
    protected View view;
    private ListView topScores_ListView;

    public static Fragment_List newInstance() {
        return new Fragment_List();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        if (view == null){
            view = inflater.inflate(R.layout.fragment_list, container, false);
        }
        topScores_ListView = view.findViewById(R.id.topScores_ListView);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Collections.sort(TopTen.getInstance().getTopTenScores(), new ScoreComparator());
        AdapterForListView adapterForListView = new AdapterForListView(getActivity(),  TopTen.getInstance().getTopTenScores());
        topScores_ListView.setAdapter(adapterForListView);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Score score = (Score)topScores_ListView.getItemAtPosition(i);
        //toMapListener.reachLocation(score.getLat(), score.getLon());

    }

}

