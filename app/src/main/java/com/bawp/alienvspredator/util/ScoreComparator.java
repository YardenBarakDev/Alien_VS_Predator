package com.bawp.alienvspredator.util;

import com.bawp.alienvspredator.model.Score;

import java.util.Comparator;

//sort ArrayList<Score> topTenScores by who has to lowest number of moves
public class ScoreComparator implements Comparator<Score> {

    @Override
    public int compare(Score score, Score t1) {
        if (score.getNumOfMoves() > t1.getNumOfMoves())
            return 1;
        if (score.getNumOfMoves() == t1.getNumOfMoves())
            return 0;
        return -1;
    }
}

