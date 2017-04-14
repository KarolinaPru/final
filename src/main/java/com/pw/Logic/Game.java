package com.pw.Logic;

import com.pw.Logic.Exceptions.IllegalNumberOfQuestionsException;
import com.pw.Logic.Exceptions.IllegalTimeOfAnswerSubmissionException;
import com.pw.Logic.Exceptions.ScoreCannotBeRetrievedBeforeGameIsClosedException;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */
public class Game {
    private static final int appropriateNumberOfQuestions = 10;
    private static final int answerTimeForSingleQuestionInSeconds = 15;
    private static final int timeFrameForAnswerSubmissionInSeconds = 10;
    private int timeUntilAnswerEvaluationInSeconds = appropriateNumberOfQuestions * answerTimeForSingleQuestionInSeconds;
    private int timeUntilGameClosureInSeconds = timeUntilAnswerEvaluationInSeconds + timeFrameForAnswerSubmissionInSeconds;
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
        if (questions.size() != appropriateNumberOfQuestions) {
            throw new IllegalNumberOfQuestionsException(); // TODO: +xUT (check if thrown, check if category and questions were assigned)
        }

        this.category = category;
        this.questions = questions;
    }

    public List<Score> getScores() throws ScoreCannotBeRetrievedBeforeGameIsClosedException {
        determineCurrentState();

        if (currentState != GameState.CLOSED) {
            throw new ScoreCannotBeRetrievedBeforeGameIsClosedException(); // TODO: 1UT
        }

        if (winnerIsNotDetermined()) { // TODO: 1UT
            Score winningScore = findWinningScore(); // TODO: 1UT
            winningScore.markAsHighest(); // TODO: 1UT
        }
        return scores; // TODO: 1UT
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

    protected void evaluateAnswers(Player player, List<Answer> submittedAnswers) throws IllegalTimeOfAnswerSubmissionException {
        determineCurrentState();

        if (currentState != GameState.STARTED && currentState != GameState.EVALUATING_ANSWERS) {
            throw new IllegalTimeOfAnswerSubmissionException();
        }

        if (scores.stream().filter(score -> score.getPlayer().equals(player)).count() > 0) {
            return;
        }

        Score score = new Score(player); // TODO (optional): 3UT (only if score is injected or created by factory (which also should be constructor-injected))
        score.evaluateAnswers(submittedAnswers);
        scores.add(score);
    }

    private void determineCurrentState() {
        LocalDateTime now = LocalDateTime.now();

        if (startTime.plusSeconds(timeUntilGameClosureInSeconds).isBefore(now)) { // TODO (optional): 2UT (Check state for 'now' before and after)
            currentState = GameState.CLOSED;
        } else if (startTime.plusSeconds(timeUntilAnswerEvaluationInSeconds).isBefore(now) && startTime.plusSeconds(timeUntilGameClosureInSeconds).isAfter(now)) { // TODO (optional): 2UT (Check state for 'now' before and after)
            currentState = GameState.EVALUATING_ANSWERS;
        }
    }

    private boolean winnerIsNotDetermined() {
        return scores.stream().filter(score -> score.isHighest()).count() == 0;
    }

    private Score findWinningScore() {
        return scores.stream().sorted(Comparator.comparingInt(Score::getPoints).reversed()).findFirst().get();
    }
}
