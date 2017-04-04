package com.pw.Logic;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */

// Odliczanie czasu oraz wyswietlanie kolejnych pytań  beda lezec po stronie przegladarki
@Getter
public class GameImpl {

    private static long nextAvailableId;
    private long id = 1;
    private Category category;
    private Player gameOwner;
    private Player winner;
    private List<Player> players = new ArrayList<>();
    private final QuestionService questionService;
    private List<Question> questions;
    private Map<Player, Integer> scores = new HashMap<>();
    private boolean isStarted;
    private boolean isOpen;
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

    public int evaluateAnswers(Player player, List<Answer> submittedAnswers) {

        int score = 0;
        Answer answer;

        for (int i = 0; i < submittedAnswers.size(); i++) {
            answer = submittedAnswers.get(i);
            if(answer.isCorrect()) {
                score += 10;
            }
        }

        player.addXp(score);
        player.incrementGamesPlayed();
        gatherScores(player, score);

        return score;
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

    public Player determineWinner(Map<Player, Integer> scores) {

        //TODO:
        // Make sure all the players have submitted their answers to determine the actual winner

        if (scores != null && scores.size() == players.size() && players.size() > 1) {
            winner = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();

            winner.addXp(30);
        }
            return winner;

    }

    private Map<Player, Integer> gatherScores(Player player, int score) {
        scores.put(player, score);

        return scores;
    }
}
