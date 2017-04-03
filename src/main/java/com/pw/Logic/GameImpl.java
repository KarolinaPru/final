package com.pw.Logic;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */

// Odliczanie czasu oraz wyswietlanie kolejnych pytań  beda lezec po stronie przegladarki

public class GameImpl {

    private static long nextAvailableId;
    @Getter
    private long id = 1;
    @NonNull
    private Category category;
    @NonNull
    private Player gameOwner;
    @Getter
    private List<Player> players = new ArrayList<>();
    @NonNull
    private final QuestionService questionService;
    private List<Question> questions;
    @Getter
    private List<Answer> answers;
    @Getter
    private boolean isStarted;
    @Getter
    private boolean isOpen;
    @Getter
    private LocalDateTime startTime;


    public GameImpl(Player gameOwner, Category category, QuestionService questionService) {
        if (gameOwner == null || category == null) {
            throw new IllegalArgumentException("The game cannot be initiated without the game admin and a category.");
        }
        this.gameOwner = gameOwner;
        this.category = category;
        this.questionService = questionService;

        id = nextAvailableId;
        nextAvailableId++;
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

    public List<Question> getQuestions(){
        questions = questionService.get10RandomQuestions(category);
        return questions;
    }

    public void start() {

        if (questions == null || questions.size() < 10) {
            isStarted = false;
            return;
        }

        if (!players.contains(gameOwner)) {
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


    public int evaluateAnswers(Player player, List<Answer> submittedAnswers) {

        int score = 0;
        Answer answer;

        for (int i = 0; i < submittedAnswers.size(); i++) {
            answer = submittedAnswers.get(i);
            if(answer.isCorrect()) {
                score += 10;
            }
        }

        int xp = player.getXp();
        int gainedXp = xp += score;
        player.setXp(gainedXp);

        player.incrementGamesPlayed();

        return score;
    }


}