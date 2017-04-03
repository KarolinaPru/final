package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karolina on 25.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private Player gameOwner;
    @Mock
    private Player player1;
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
        game.getQuestions();
        game.start();

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


        int expectedScore = 60;
        int actualScore = game.evaluateAnswers(player1, submittedAnswers);

        assertThat(actualScore).isEqualTo(expectedScore);
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
