package com.pw.Logic;

import com.pw.Logic.Exceptions.IllegalTimeOfAnswerSubmissionException;

import java.util.List;

/**
 * Created by Karolina on 14.04.2017.
 */
// Ultimately PlayerInGame will be created based on the Player and Game retrieved from the DB in a REST API call
public class PlayerInGame extends Player {
    private final Game game;
    private final boolean isOwner;

    public PlayerInGame(String name, Game game, boolean isOwner) {
        super(name);
        this.game = game;
        this.isOwner = isOwner;

        game.addPlayer(this); // TODO: +1UT (check if called)
    }

    public void startGame() {
        if (this.isOwner)
            game.start();  // TODO: +2UT
    }

    public void submitAnswers(List<Answer> answers) throws IllegalTimeOfAnswerSubmissionException {
        if (answers == null) {
            return; // TODO: +1UT (check if not called)
        }

        game.evaluateAnswers(this, answers); // TODO: +1UT (check if called)
    }
}
