package com.pw.Logic;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */
public class Game {
    @Getter
    private final Category category;
    @Getter
    private final List<Question> questions;
    @Getter
    private List<Player> players = new ArrayList<>();
    @Getter
    private GameState currentState = GameState.OPEN;
    @Getter
    private LocalDateTime startTime;
    private List<Score> scores = new ArrayList<>();

    public Game(Category category, List<Question> questions) throws IllegalNumberOfQuestionsException {
        if (questions.size() != 10) {
            throw new IllegalNumberOfQuestionsException();
        }

        this.category = category;
        this.questions = questions;
    }

    public List<Score> getScores() {
        if (winnerIsNotDetermined()) {
            Score winningScore = findWinningScore();
            winningScore.markAsHighest();
        }
        return scores;
    }

    protected void addPlayer(Player player) {
        if (players.contains(player)) {
            return;
        }

        players.add(player);
    }

    protected void start() {
        currentState = GameState.STARTED;
        startTime = LocalDateTime.now();
    }

    protected void evaluateAnswers(Player player, List<Answer> submittedAnswers) {
        if (scores.stream().filter(score -> score.getPlayer().equals(player)).count() > 0) {
            return;
        }

        Score score = new Score(player);
        score.evaluateAnswers(submittedAnswers);
        scores.add(score);
    }

    private boolean winnerIsNotDetermined() {
        return scores.stream().filter(score -> score.isHighest()).count() == 0;
    }

    private Score findWinningScore() {
        return scores.stream().sorted(Comparator.comparingInt(Score::getPoints).reversed()).findFirst().get();
    }
}
