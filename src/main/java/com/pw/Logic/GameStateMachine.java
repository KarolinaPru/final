package com.pw.Logic;

import lombok.Getter;

import java.time.LocalDateTime;

public class GameStateMachine {
    static final int answerTimeForSingleQuestionInSeconds = 15;
    static final int timeFrameForAnswerSubmissionInSeconds = 10;
    int timeUntilAnswerEvaluationInSeconds;
    int timeUntilGameClosureInSeconds = timeUntilAnswerEvaluationInSeconds + timeFrameForAnswerSubmissionInSeconds;
    @Getter
    GameState currentState;
    @Getter
    LocalDateTime startTime;

    protected GameStateMachine(int appropriateNumberOfQuestions) {
        this.timeUntilAnswerEvaluationInSeconds = appropriateNumberOfQuestions * answerTimeForSingleQuestionInSeconds;
        this.currentState = GameState.OPEN;
    }

    protected void determineCurrentState() {
        LocalDateTime now = LocalDateTime.now();

        if (startTime.plusSeconds(timeUntilGameClosureInSeconds).isBefore(now)) { // TODO (optional): 2UT (Check state for 'now' before and after)
            currentState = GameState.CLOSED;
        } else if (startTime.plusSeconds(timeUntilAnswerEvaluationInSeconds).isBefore(now) && startTime.plusSeconds(timeUntilGameClosureInSeconds).isAfter(now)) { // TODO (optional): 2UT (Check state for 'now' before and after)
            currentState = GameState.EVALUATING_ANSWERS;
        }
    }

    protected void start() {
        currentState = GameState.STARTED;
        startTime = LocalDateTime.now();
    }

    protected boolean gameIsClosed() {
        return currentState == GameState.CLOSED;
    }

    protected boolean gameIsNotInProgress() {
        return currentState != GameState.STARTED && currentState != GameState.EVALUATING_ANSWERS;
    }
}