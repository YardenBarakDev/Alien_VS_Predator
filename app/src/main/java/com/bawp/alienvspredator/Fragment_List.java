package com.bawp.alienvspredator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Collections;

public class Fragment_List extends Fragment {

    protected View view;
    private ListView topScores_ListView;
    private CallBack_ListToMap callBack_listToMap;

    public void setActivityCallBack(CallBack_ListToMap activityCallBack){
        this.callBack_listToMap = activityCallBack;
    }

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

        findViews();
        topScores_ListView.setOnItemClickListener(onItemClickListener);
        return view;
    }

    private void findViews() {
        topScores_ListView = view.findViewById(R.id.topScores_ListView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Sort the top ten array
        Collections.sort(TopTen.getInstance().getTopTenScores(), new ScoreComparator());

        //create adapter so the array can be present in the list with all the widgets
        AdapterForListView adapterForListView = new AdapterForListView(getActivity(),  TopTen.getInstance().getTopTenScores());
        topScores_ListView.setAdapter(adapterForListView);

    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Score score = (Score)topScores_ListView.getItemAtPosition(i);
            callBack_listToMap.reachLocation(score.getLat(), score.getLon());
        }
    };

}

