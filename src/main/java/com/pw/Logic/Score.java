package com.pw.Logic;

import lombok.Getter;
import java.util.List;

/**
 * Created by Karolina on 14.04.2017.
 */
@Getter
public class Score {

    private final Player player;
    private boolean isHighest;
    private int points;

    protected Score(Player player) {
        this.player = player;
    }

    protected void evaluateAnswers(List<Answer> submittedAnswers) {
        for (int i = 0; i < submittedAnswers.size(); i++) {
            Answer answer = submittedAnswers.get(i);
            if (answer.isCorrect()) {
                points += 10;
            }
        }

        player.addXp(points); // TODO: +3UT (check if called with correct number of points), 1UT for 0 correct answers, 1UT for 1 correct answer, 1UT for more than 1
        player.incrementGamesPlayed(); // TODO: +1UT (check if called)
    }

    protected void markAsHighest() {
        isHighest = true; // TODO: +1UT (check if called)
        player.addXp(30); // TODO: +1UT (check if called with argument)
    }
}
