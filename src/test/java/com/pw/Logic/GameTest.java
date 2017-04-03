package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Karolina on 25.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private Player gameOwner;
    private Player player1 = new PlayerImpl("Janek");
    @Mock
    private Player player2;
    @Mock
    private Player player3;
    @Mock
    private List<Question> questions;
    @Mock
    private QuestionService questionService;
    @Mock
    private Category category;
    private ArrayList<Answer> submittedAnswers;
    private Map<Player, Integer> scores;

    @Test
    public void GivenNoPlayer_WhenInstantiatingGame_ThenIllegalArgumentExceptionExceptionShouldBeThrown(){

        assertThatThrownBy(() -> new GameImpl(null, category, questionService))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void GivenNoCategory_WhenInstantiatingGame_ThenIllegalArgumentExceptionExceptionShouldBeThrown(){

        assertThatThrownBy(() -> new GameImpl(gameOwner, null, questionService))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void GivenPlayerAndCategory_WhenInstantiatingGame_ThenItShouldNotBeNull() {

        GameImpl gameOne = new GameImpl(gameOwner, category, questionService);
        assertThat(gameOne).isNotNull();
    }

    @Test
    public void GivenCreatedGameWithGameOwner_WhenNewPlayersAreJoining_ThenTheyShouldBeAddedToListOfGamePlayers() {

        GameImpl game = arrangeGameOfOwnerAndTwoPlayers();

        assertThat(game.getPlayers().get(0).equals(gameOwner));
        assertThat(game.getPlayers().get(1).equals(player1));
        assertThat(game.getPlayers().get(2).equals(player2));
    }

    @Test
    public void GivenOwnerAndCategory_WhenCreatingNewGame_ThenIdShouldBeAssignedAndNotBeNull(){
        GameImpl game = arrangeGameOfOwnerAndTwoPlayers();

        assertThat(game).hasFieldOrProperty("id");
        assertThat(game.getId()).isNotNull();
    }

    @Test
    public void GivenGameIsCreated_WhenCheckedIfIsOpen_ThenTrueShouldBeReturned(){
        GameImpl game = arrangeGameOfOwnerAndTwoPlayers();

        assertThat(game.isOpen()).isTrue();
    }

    @Test
    public void GivenListOf4Players_WhenPlayer2IsRemoved_ThenListShouldContain3PlayersAndPlayer2ShouldBeExcluded(){

        GameImpl game = arrangeGameOfOwnerAndTwoPlayers();
        game.addPlayer(player3);
        int playersBeforeRemoval = game.getPlayers().size();

        game.removePlayer(player2);
        int playersAfterRemoval = game.getPlayers().size();

        assertThat(playersBeforeRemoval).isEqualTo(4);
        assertThat(playersAfterRemoval).isEqualTo(3);
        assertThat(game.getPlayers()).doesNotContain(player2);
    }

    @Test
    public void GivenGameWithoutOwner_WhenStarting_ThenGameShouldNotStart() {
        GameImpl game = arrangeGameOfOwnerAndTwoPlayers();

        game.removePlayer(gameOwner);
        game.start();

        assertThat(game.isStarted()).isFalse();
    }

    @Test
    public void GivenFewerThan10Questions_WhenStartingGame_ThenItShouldNotStart(){

        arrangeListOf9Questions();
        GameImpl game = arrangeGameOfOwnerAndTwoPlayers();

        game.start();

        assertThat(game.isStarted()).isFalse();
    }

    @Test
    public void GivenNullListOfQuestions_WhenStartingGame_ThenItShouldNotStart(){

        Mockito.when(questionService.get10RandomQuestions(any())).thenReturn(null);
        GameImpl game = arrangeGameOfOwnerAndTwoPlayers();

        game.start();

        assertThat(game.isStarted()).isFalse();
    }

    @Test
    public void GivenGameOwnerAnd10QuestionsInCategory_WhenStartingGame_ThenItShouldStart(){

        GameImpl game = arrangePositiveGameConditions();

        game.getQuestions();
        game.start();

        assertThat(game.isStarted()).isTrue();
    }

    @Test
    public void GivenGameConditionsAreMet_WhenStarting_ThenStartTimeShouldBeAssigned() {
        GameImpl game = arrangePositiveGameConditions();

        game.getQuestions();
        game.start();

        assertThat(game).hasFieldOrProperty("startTime");
        assertThat(game.getStartTime()).isNotNull();
    }

    @Test
    public void GivenGameConditionsAreNotMet_WhenStarting_ThenStartTimeShouldBeNull() {
        arrangeListOf9Questions();
        GameImpl game = arrangeGameOfOwnerAndTwoPlayers();

        game.start();

        assertThat(game).hasFieldOrProperty("startTime");
        assertThat(game.getStartTime()).isNull();
    }

    @Test
    public void GivenPlayer_WhenEndingGame_ThenPlayerShouldBeRemoved() {
        GameImpl game = arrangePositiveGameConditions();
        game.start();

        game.end(player1);

        assertThat(game.getPlayers()).doesNotContain(player1);
    }

    @Test
    public void GivenAllPlayersEndedGame_WhenCheckingIfIsOpenAndStarted_ThenFalseShouldBeReturned() {
        GameImpl game = arrangePositiveGameConditions();
        game.start();

        game.end(player1);
        game.end(player2);
        game.end(gameOwner);

        assertThat(game.isOpen()).isFalse();
        assertThat(game.isStarted()).isFalse();
    }

    @Test
    public void GivenNotAllPlayersEndedGame_WhenCheckingIfIsOpenAndStarted_ThenTrueShouldBeReturned() {
        GameImpl game = arrangePositiveGameConditions();
        game.start();

        game.end(player1);
        game.end(gameOwner);

        assertThat(game.isOpen()).isTrue();
        assertThat(game.isStarted()).isTrue();
    }

    @Test
    public void GivenGameWasStarted_WhenEvaluatingUsersAnswers_ThenCorrectScoreShouldBeReturned() {
        GameImpl game = arrangePositiveGameConditions();
        getQuestionsStartGameAndGetSubmittedAnswers(game);

        int expectedScore = 60;
        int actualScore = game.evaluateAnswers(player1, submittedAnswers);

        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void GivenEvaluatingAnswers_WhenCalled_ThenPlayerShouldReceiveXP(){
        GameImpl game = arrangePositiveGameConditions();
        getQuestionsStartGameAndGetSubmittedAnswers(game);

        int initialXp = player1.getXp();
        int score = game.evaluateAnswers(player1, submittedAnswers);

        int expectedXp = initialXp + score;

        assertThat(player1.getXp()).isEqualTo(expectedXp);
    }

    @Test
    public void GivenEvaluatingAnswers_WhenCalled_ThenPlayersGamePlayedShouldBeUpdated(){

        int initialGamesPlayed = player1.getGamesPlayed();
        GameImpl game = arrangePositiveGameConditions();
        getQuestionsStartGameAndGetSubmittedAnswers(game);

        game.evaluateAnswers(player1, submittedAnswers);

        GameImpl game2 = arrangePositiveGameConditions();
        getQuestionsStartGameAndGetSubmittedAnswers(game2);

        game2.evaluateAnswers(player1, submittedAnswers);

        int expectedGamesPlayed = initialGamesPlayed + 2;

        assertThat(player1.getGamesPlayed()).isEqualTo(expectedGamesPlayed);

    }

    @Test
    public void GivenAtLeast2Players_WhenGettingWinner_ThenOneWithHigherScoreShouldBeReturned() {
        GameImpl game = arrangePositiveGameConditions();
        getQuestionsStartGameAndGetSubmittedAnswers(game);
        int scorePlayer1 = game.evaluateAnswers(player1, submittedAnswers);
        scores = new HashMap<>();
        scores.put(player2, 0);
        scores.put(player3, 20);
        scores.put(player1, scorePlayer1);
        scores.put(gameOwner, 40);

        Player actualWinner = game.getWinner(scores);

        assertThat(actualWinner).isEqualTo(player1);
    }

    private void getQuestionsStartGameAndGetSubmittedAnswers(GameImpl game) {
        game.getQuestions();
        game.start();
        submittedAnswers = getSubmittedAnswers();
    }


    private ArrayList<Answer> getSubmittedAnswers() {
        ArrayList<Answer> submittedAnswers = new ArrayList<>();
        submittedAnswers.add(new AnswerImpl("", true));
        submittedAnswers.add(new AnswerImpl("", true));
        submittedAnswers.add(new AnswerImpl("", true));
        submittedAnswers.add(new AnswerImpl("", true));
        submittedAnswers.add(new AnswerImpl("", true));
        submittedAnswers.add(new AnswerImpl("", true));
        submittedAnswers.add(new AnswerImpl("", false));
        submittedAnswers.add(new AnswerImpl("", false));
        submittedAnswers.add(new AnswerImpl("", false));
        submittedAnswers.add(new AnswerImpl("", false));
        return submittedAnswers;
    }

    private GameImpl arrangePositiveGameConditions() {
        Mockito.when(questions.size()).thenReturn(10);
        Mockito.when(questionService.get10RandomQuestions(any())).thenReturn(questions);
        return arrangeGameOfOwnerAndTwoPlayers();
    }

    private GameImpl arrangeGameOfOwnerAndTwoPlayers() {
        GameImpl game = new GameImpl(gameOwner, category, questionService);

        game.addPlayer(player1);
        game.addPlayer(player2);
        return game;
    }

    private void arrangeListOf9Questions() {
        Mockito.when(questions.size()).thenReturn(9);
        Mockito.when(questionService.get10RandomQuestions(any())).thenReturn(questions);
    }
}
