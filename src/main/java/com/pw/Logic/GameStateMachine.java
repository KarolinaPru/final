package com.pw.Logic;

import lombok.Getter;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

public class GameStateMachine {
    Clock clock;
    static final int answerTimeForSingleQuestionInSeconds = 15;
    static final int timeFrameForAnswerSubmissionInSeconds = 10;
    int timeUntilAnswerEvaluationInSeconds;
    int timeUntilGameClosureInSeconds;
    @Getter
    GameState currentState;
    @Getter
    Instant startTime;

    protected GameStateMachine(int appropriateNumberOfQuestions, Clock clock) {
        this.timeUntilAnswerEvaluationInSeconds = appropriateNumberOfQuestions * answerTimeForSingleQuestionInSeconds;
        this.timeUntilGameClosureInSeconds = timeUntilAnswerEvaluationInSeconds + timeFrameForAnswerSubmissionInSeconds;
        this.currentState = GameState.OPEN;
        this.clock = clock;
    }

    protected void determineCurrentState() {
        Instant now = clock.instant();

        if (startTime.plusSeconds(timeUntilGameClosureInSeconds).isBefore(now)) {
            currentState = GameState.CLOSED;
        } else if (startTime.plusSeconds(timeUntilAnswerEvaluationInSeconds).isBefore(now) && startTime.plusSeconds(timeUntilGameClosureInSeconds).isAfter(now)) {
            currentState = GameState.EVALUATING_ANSWERS;
        }
    }

    protected void start() {
        currentState = GameState.STARTED;
        startTime = clock.instant();
    }

    protected boolean gameIsClosed() {
        return currentState == GameState.CLOSED;
    }

    protected boolean gameIsNotInProgress() {
        return currentState != GameState.STARTED && currentState != GameState.EVALUATING_ANSWERS;
    }
}