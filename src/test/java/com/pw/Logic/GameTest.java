package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karolina on 25.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private Player fakeGameAdmin;
    @Mock
    private Player fakePlayer1;
    @Mock
    private Player fakePlayer2;
    @Mock
    private Player fakePlayer3;

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

        assertThatThrownBy(() -> new GameImpl(fakeGameAdmin, null, questionService))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void GivenPlayerAndCategory_WhenInstantiatingGame_ThenItShouldNotBeNull() {

        GameImpl gameOne = new GameImpl(fakeGameAdmin, Category.ARTS, questionService);
        assertThat(gameOne).isNotNull();

    }

    @Test
    public void GivenCreatedGameWithGameAdmin_WhenNewPlayersAreJoining_ThenTheyShouldBeAddedToListOfGamePlayers() {

        GameImpl game = arrangeGameOfAdminAndTwoPlayers();

        assertThat(game.getPlayers().get(0).equals(fakeGameAdmin));
        assertThat(game.getPlayers().get(1).equals(fakePlayer1));
        assertThat(game.getPlayers().get(2).equals(fakePlayer2));

    }

    @Test
    public void GivenListOf4Players_WhenPlayer2IsRemoved_ThenListShouldContain3PlayersAndPlayer2ShouldBeExcluded(){

        GameImpl game = arrangeGameOfAdminAndTwoPlayers();
        game.addPlayer(fakePlayer3);

        int playersBeforeRemoval = game.getPlayers().size();

        game.removePlayer(fakePlayer2);
        int playersAfterRemoval = game.getPlayers().size();

        assertThat(playersBeforeRemoval).isEqualTo(4);
        assertThat(playersAfterRemoval).isEqualTo(3);
        assertThat(game.getPlayers()).doesNotContain(fakePlayer2);

    }

    @Test
    public void GivenGameWithoutAdmin_WhenStarting_ThenGameShouldNotStart() {
        GameImpl game = arrangeGameOfAdminAndTwoPlayers();

        game.removePlayer(fakeGameAdmin);
        game.start();

        assertThat(game.isStarted()).isFalse();
    }

    @Test
    public void GivenGameAdminAndQuestionsInCategory_WhenStartingGame_ThenItShouldBeStarted(){

        Mockito.when(questionService.getQuestions(Mockito.any())).thenReturn(questions);
        GameImpl game = new GameImpl(fakeGameAdmin, Category.MISCELLANEOUS, questionService);

        game.start();

        assertThat(game.isStarted()).isTrue();
    }


    private GameImpl arrangeGameOfAdminAndTwoPlayers() {
        GameImpl game = new GameImpl(fakeGameAdmin, Category.MISCELLANEOUS, questionService);

        game.addPlayer(fakePlayer1);
        game.addPlayer(fakePlayer2);
        return game;
    }
}
