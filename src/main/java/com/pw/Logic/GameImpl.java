package com.pw.Logic;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */

// Odliczanie czasu oraz wyswietlanie kolejnych pytań  beda lezec po stronie przegladarki
public class GameImpl {

    public static long getNextAvailableId() {
        return nextAvailableId;
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public Player getGameOwner() {
        return gameOwner;
    }

    public Player getWinner() {
        return winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public Map<Player, Integer> getScores() {
        return scores;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public
    GameImpl(Player gameOwner, Category category, QuestionService questionService) {
        if (gameOwner == null || category == null || questionService == null) {
            throw new IllegalArgumentException("The game cannot be initiated.");
        }
        this.gameOwner = gameOwner;
        this.category = category;
        this.questionService = questionService;

        id = nextAvailableId;
        nextAvailableId++;
        isOpen = true;
        players.add(gameOwner);
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        if (players != null && players.contains(player)) {
            players.remove(player);
        }
    }

    public List<Question> obtainQuestions() throws NoQuestionsInCategoryException {

        if (questionService != null) {
            questions = questionService.get10RandomQuestions(category);

//            if (questions == null) {
//                throw new NoQuestionsInCategoryException();
//            }
        }

        // TODO: if questions could't be retrieved, throw an exception (choose a different category)
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

        if (submittedAnswers != null && submittedAnswers.size() == 10) {

            for (int i = 0; i < submittedAnswers.size(); i++) {
                answer = submittedAnswers.get(i);
                if (answer.isCorrect()) {
                    score += 10;
                }
            }

            player.addXp(score);
            player.incrementGamesPlayed();
            gatherScores(player, score);
        }
        return score;
    }

    public Player determineWinner(Map<Player, Integer> scores) {

        if (scores == null) {
            throw new IllegalArgumentException("There are no scores.");
        }
        if (scores != null && scores.size() > 1) {
            winner = findPlayerWithHighestScore(scores);
            winner.addXp(30);
        } else {
            Set keys = scores.keySet();
            winner = getTheFirstAndOnlyPlayerFromKeySet(keys);
        }
        return winner;
    }

    private Player getTheFirstAndOnlyPlayerFromKeySet(Set keys) {
        return (Player) keys.toArray()[0];
    }

    public void end(Player player) {

        removePlayer(player);

        if (players.size() == 0) {
            isOpen = false;
            isStarted = false;
        } else {
            isOpen = true;
            isStarted = true;
        }
    }

    private Map<Player, Integer> gatherScores(Player player, int score) {
        scores.put(player, score);
        return scores;
    }

    private Player findPlayerWithHighestScore(Map<Player, Integer> scores) {
        return Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static long nextAvailableId;
    private long id = 1;
    private Category category;
    private Player gameOwner;
    private Player winner;
    private List<Player> players = new ArrayList<>();
    private final QuestionService questionService;
    private List<Question> questions;
    private Map<Player, Integer> scores = new LinkedHashMap<>();
    private boolean isStarted;
    private boolean isOpen;
    private LocalDateTime startTime;
}
