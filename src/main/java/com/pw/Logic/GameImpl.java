package com.pw.Logic;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */

// Odliczanie czasu, wyswietlanie kolejnych pytan i poprawnych odpowiedzi
// oraz naliczanie punktow za gre beda lezec po stronie przegladarki

public class GameImpl {

    @NonNull
    private Player gameOwner;
    @NonNull
    private Category category;
    @Getter
    private List<Player> players = new ArrayList<>();
    private List<Question> questions;
    @Getter
    private boolean isStarted;
    @Getter
    private boolean isOpen;
    @NonNull
    private final QuestionService questionService;
    @Getter
    private LocalDateTime startTime;
    @Getter
    private UUID id;


    public GameImpl(Player gameOwner, Category category, QuestionService questionService) {
        if(gameOwner == null || category == null) {
            throw new IllegalArgumentException("The game cannot be initiated without the game admin and a category.");
        }
        this.gameOwner = gameOwner;
        this.category = category;
        this.questionService = questionService;

        id = UUID.randomUUID();
        isOpen = true;
        players.add(gameOwner);
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

        if(!players.contains(gameOwner)) {
            isStarted = false;
            return;
        }

        isStarted = true;
        startTime = LocalDateTime.now();
    }

    public void end(Player player) {

        players = removePlayer(player);

        if (players.size() == 0) {
            isOpen = false;
            isStarted = false;
        } else {
            isOpen = true;
            isStarted = true;
        }
    }
}