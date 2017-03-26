package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Karolina on 25.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private Player gameAdmin;
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

    @Test
    public void GivenNoPlayer_WhenInstantiatingGame_ThenIllegalArgumentExceptionExceptionShouldBeThrown(){

        assertThatThrownBy(() -> new GameImpl(null, Category.MISCELLANEOUS, questionService))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void GivenNoCategory_WhenInstantiatingGame_ThenIllegalArgumentExceptionExceptionShouldBeThrown(){

        assertThatThrownBy(() -> new GameImpl(gameAdmin, null, questionService))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void GivenPlayerAndCategory_WhenInstantiatingGame_ThenItShouldNotBeNull() {

        GameImpl gameOne = new GameImpl(gameAdmin, Category.ARTS, questionService);
        assertThat(gameOne).isNotNull();

    }

    @Test
    public void GivenCreatedGameWithGameAdmin_WhenNewPlayersAreJoining_ThenTheyShouldBeAddedToListOfGamePlayers() {

        GameImpl game = arrangeGameOfAdminAndTwoPlayers();

        assertThat(game.getPlayers().get(0).equals(gameAdmin));
        assertThat(game.getPlayers().get(1).equals(player1));
        assertThat(game.getPlayers().get(2).equals(player2));

    }

    @Test
    public void GivenListOf4Players_WhenPlayer2IsRemoved_ThenListShouldContain3PlayersAndPlayer2ShouldBeExcluded(){

        GameImpl game = arrangeGameOfAdminAndTwoPlayers();
        game.addPlayer(player3);
        int playersBeforeRemoval = game.getPlayers().size();

        game.removePlayer(player2);
        int playersAfterRemoval = game.getPlayers().size();

        assertThat(playersBeforeRemoval).isEqualTo(4);
        assertThat(playersAfterRemoval).isEqualTo(3);
        assertThat(game.getPlayers()).doesNotContain(player2);

    }

    @Test
    public void GivenGameWithoutAdmin_WhenStarting_ThenGameShouldNotStart() {
        GameImpl game = arrangeGameOfAdminAndTwoPlayers();

        game.removePlayer(gameAdmin);
        game.start();

        assertThat(game.isStarted()).isFalse();
    }

    @Test
    public void GivenFewerThan10Questions_WhenStartingGame_ThenItShouldNotStart(){

        Mockito.when(questions.size()).thenReturn(9);
        Mockito.when(questionService.get10RandomQuestions(Mockito.any())).thenReturn(questions);
        GameImpl game = arrangeGameOfAdminAndTwoPlayers();

        game.start();

        assertThat(game.isStarted()).isFalse();
    }

    @Test
    public void GivenNullListOfQuestions_WhenStartingGame_ThenItShouldNotStart(){

        Mockito.when(questionService.get10RandomQuestions(Mockito.any())).thenReturn(null);
        GameImpl game = arrangeGameOfAdminAndTwoPlayers();

        game.start();

        assertThat(game.isStarted()).isFalse();
    }

    @Test
    public void GivenGameAdminAnd10QuestionsInCategory_WhenStartingGame_ThenItShouldStart(){

        GameImpl game = arrangeGameConditions();

        game.start();

        assertThat(game.isStarted()).isTrue();
    }

    @Test
    public void GivenGameConditionsArranged_WhenStarting_ThenStartTimeShouldBeAssigned() {
        GameImpl game = arrangeGameConditions();

        game.start();

        assertThat(game).hasFieldOrProperty("startTime");
        assertThat(game.getStartTime()).isNotNull();

    }

    private GameImpl arrangeGameConditions() {
        Mockito.when(questions.size()).thenReturn(10);
        Mockito.when(questionService.get10RandomQuestions(Mockito.any())).thenReturn(questions);
        return arrangeGameOfAdminAndTwoPlayers();
    }

    private GameImpl arrangeGameOfAdminAndTwoPlayers() {
        GameImpl game = new GameImpl(gameAdmin, Category.MISCELLANEOUS, questionService);

        game.addPlayer(player1);
        game.addPlayer(player2);
        return game;
    }
}
