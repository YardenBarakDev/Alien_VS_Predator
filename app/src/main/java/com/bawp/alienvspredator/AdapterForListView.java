package com.bawp.alienvspredator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForListView extends BaseAdapter {

    Context context;
    ArrayList<Score> topTenScores;

    public AdapterForListView(Context context, ArrayList<Score> topTenScores){
        this.context = context;
        this.topTenScores = topTenScores;
    }
    @Override
    public int getCount() {
        return topTenScores.size();
    }

    @Override
    public Object getItem(int i) {
        return topTenScores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listview_rows, viewGroup, false);
        }

        Score score = (Score) getItem(i);
        if (score.getNumOfMoves() != 1)
        {
            TextView listView_textView_nameAndScore = view.findViewById(R.id.listView_textView_nameAndScore);
            TextView listView_textView_time = view.findViewById(R.id.listView_textView_time);
            ImageView listView_Row_Image = view.findViewById(R.id.listView_Row_Image);

            String info = "Number of moves " + topTenScores.get(i).getNumOfMoves();
            listView_textView_nameAndScore.setText(info);
            listView_textView_time.setText(score.getTimeStamp());
            if (score.getWinner())
                listView_Row_Image.setImageResource(R.drawable.predator_icon_for_listview);
            else
                listView_Row_Image.setImageResource(R.drawable.alien_icon_for_listview);
        }
        return view;
    }
}