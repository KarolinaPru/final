package com.pw.Logic;


import lombok.Getter;

import java.util.List;

/**
 * Created by Karolina on 14.04.2017.
 */
public class Score {

    @Getter
    private final Player player;
    @Getter
    private boolean isHighest;
    @Getter
    private int points;

    protected Score(Player player) {
        this.player = player;
    }

    protected void evaluateAnswers(List<Answer> submittedAnswers) {
        for (int i = 0; i < submittedAnswers.size(); i++) {
            Answer answer = submittedAnswers.get(i);
            if (answer.isCorrect()) {
                this.points += 10;
            }
        }

        player.addXp(this.points);
        player.incrementGamesPlayed();
    }

    protected void markAsHighest() {
        isHighest = true;
        player.addXp(30);
    }
}
