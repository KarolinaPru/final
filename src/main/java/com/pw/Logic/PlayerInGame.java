package com.pw.Logic;

import java.util.List;

/**
 * Created by Karolina on 14.04.2017.
 */
public class PlayerInGame extends Player {
    private final Game game;
    private final boolean isOwner;

    public PlayerInGame(String name, Game game, boolean isOwner) {
        super(name);
        this.game = game;
        this.isOwner = isOwner;

        game.addPlayer(this);
    }

    public void startGame() {
        if (this.isOwner)
            game.start();
    }

    public void submitAnswers(List<Answer> answers) {
        if (answers == null) {
            return;
        }

        game.evaluateAnswers(this, answers);
    }
}
