package com.pw.Logic;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */


public class GameImpl {

    @NonNull
    private Player gameAdmin;
    @NonNull
    private Category category;
    @Getter
    private List<Player> players = new ArrayList<>();
    private List<Question> questions;
    @Getter
    private boolean isStarted;
    @NonNull
    private final QuestionService questionService;
    @Getter
    private LocalDateTime startTime;
    @Getter
    private UUID id;


    public GameImpl(Player gameAdmin, Category category, QuestionService questionService) {
        if(gameAdmin == null || category == null) {
            throw new IllegalArgumentException("The game cannot be initiated without the game admin and a category.");
        }
        this.gameAdmin = gameAdmin;
        this.category = category;
        this.questionService = questionService;

        id = UUID.randomUUID();
        players.add(gameAdmin);
    }

    public List<Player> addPlayer(Player player) {
        players.add(player);

        return players;
    }

    public List<Player> removePlayer(Player player) {
        players.remove(player);

        return players;
    }

    public void start() {

        questions = questionService.get10RandomQuestions(category);

        if (questions == null || questions.size() < 10) {
            isStarted = false;
            return;
        }

        if(!players.contains(gameAdmin)) {
            isStarted = false;
            return;
        }

        isStarted = true;
        startTime = LocalDateTime.now();
    }
}