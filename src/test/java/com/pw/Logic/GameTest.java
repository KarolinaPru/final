package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

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
    private ArrayList<QuestionInterface> fakeQuestions;


    @Test
    public void GivenNoPlayer_WhenInstantiatingGame_ThenIllegalArgumentExceptionExceptionShouldBeThrown(){

        assertThatThrownBy(() -> new GameImpl(null, Category.MISCELLANEOUS))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void GivenNoCategory_WhenInstantiatingGame_ThenIllegalArgumentExceptionExceptionShouldBeThrown(){

        assertThatThrownBy(() -> new GameImpl(fakeGameAdmin, null))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void GivenPlayerAndCategory_WhenInstatiatingGame_ThenItShouldNotBeNull() {

        GameImpl gameOne = new GameImpl(fakeGameAdmin, Category.ARTS);
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
    public void GivenCategoryWithNoQuestions_WhenStarting_ThenIllegalArgumentExceptionShouldBeThrown() {
        GameImpl game = arrangeGameOfAdminAndTwoPlayers();

        assertThatThrownBy(() -> game.start())
                .isInstanceOf(IllegalArgumentException.class);


    }

    @Test
    public void GivenGameAdminAndQuestionsInCategory_WhenStartingGame_ThenItShouldBeStarted(){


        GameImpl game = new GameImpl(fakeGameAdmin, Category.MISCELLANEOUS);
        game.start();

        assertThat(game.getQuestions()).isNotEmpty();
        assertThat(game.isStarted()).isTrue();

    }


    private GameImpl arrangeGameOfAdminAndTwoPlayers() {
        GameImpl game = new GameImpl(fakeGameAdmin, Category.MISCELLANEOUS);

        game.addPlayer(fakePlayer1);
        game.addPlayer(fakePlayer2);
        return game;
    }
}
