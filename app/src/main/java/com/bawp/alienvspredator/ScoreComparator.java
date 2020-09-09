package com.bawp.alienvspredator;

import java.util.Comparator;

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

