package com.pw.Logic;

import lombok.Data;
import lombok.NonNull;

import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */

@Data
public class GameImpl {

    @NonNull
    private Player gameAdmin;
    @NonNull
    private Category category;
    private ArrayList<Player> players = new ArrayList<>();
    private List<Question> questions;
    private boolean isStarted;
    @NonNull
    private final QuestionService questionService;


    public GameImpl(Player gameAdmin, Category category, QuestionService questionService) {
        if(gameAdmin == null || category == null) {
            throw new IllegalArgumentException("The game cannot be initiated without the game admin and a category.");
        }
        this.gameAdmin = gameAdmin;
        this.category = category;
        this.questionService = questionService;

        players.add(gameAdmin);

    }

    public ArrayList<Player> addPlayer(Player player) {
        players.add(player);

        return players;
    }

    public ArrayList<Player> removePlayer(Player player) {
        players.remove(player);

        return players;
    }

    public void start() {

        questions = questionService.getQuestions(category);

        if (questions == null) {
            isStarted = false;
            return;
        }

        if(!players.contains(gameAdmin)) {
            isStarted = false;
            return;
        }

        isStarted = true;
    }
}