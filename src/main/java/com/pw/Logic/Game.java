package com.pw.Logic;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */

// Odliczanie czasu oraz wyswietlanie kolejnych pytań  beda lezec po stronie przegladarki
public class Game {

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

    public Map<Player, Integer> getScores() {
        return scores;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Game(Player gameOwner, Category category, List<Question> questions) {
        this.gameOwner = gameOwner;
        this.category = category;
        this.questions = questions;

        id = nextAvailableId;
        nextAvailableId++;
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

    public void start() {

        if (questions == null || questions.size() < 10) {
            return;
        }

        if (!players.contains(gameOwner)) {
            return;
        }

        currentState = GameState.STARTED;
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
            currentState = GameState.ENDED;
        } else {
            currentState = GameState.CALCULATING_SCORE;
        }
    }

    private Map<Player, Integer> gatherScores(Player player, int score) {
        scores.put(player, score);
        return scores;
    }

    private Player findPlayerWithHighestScore(Map<Player, Integer> scores) {
        return Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Getter
    private static long nextAvailableId;
    @Getter
    private long id = 1;
    private Category category;
    private Player gameOwner;
    private Player winner;
    private List<Player> players = new ArrayList<>();
    private List<Question> questions;
    private Map<Player, Integer> scores = new LinkedHashMap<>();
    private LocalDateTime startTime;
    @Getter
    private GameState currentState = GameState.CREATED;
}
