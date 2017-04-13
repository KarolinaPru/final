package com.pw.Logic;

import lombok.Getter;

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

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isOpen() {
        return isOpen;
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
        isOpen = true;
        players.add(gameOwner);
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        // tylko zanim state == started
        if (players != null && players.contains(player)) {
            players.remove(player);
        }
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
    private boolean isStarted;
    private boolean isOpen;
    private LocalDateTime startTime;
}
