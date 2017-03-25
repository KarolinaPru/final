package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Karolina on 25.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private PlayerInterface fakeGameAdmin;
    @Mock
    private PlayerInterface fakePlayer1;
    @Mock
    private PlayerInterface fakePlayer2;
    @Mock
    private PlayerInterface fakePlayer3;
    @Mock
    private Category fakeCategory;


    @Test
    public void GivenNoPlayer_WhenInstantiatingGame_ThenIllegalArgumentExceptionExceptionShouldBeThrown(){

        assertThatThrownBy(() -> new Game(null, fakeCategory))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void GivenNoCategory_WhenInstantiatingGame_ThenIllegalArgumentExceptionExceptionShouldBeThrown(){

        assertThatThrownBy(() -> new Game(fakeGameAdmin, null))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void GivenPlayerAndCategory_WhenInstatiatingGame_ThenItShouldNotBeNull() {

        Game gameOne = new Game(fakeGameAdmin, fakeCategory);
        assertThat(gameOne).isNotNull();

    }

    @Test
    public void GivenGameAdmin_WhenStartingGame_ThenItShouldBeStarted(){

        Game game = new Game(fakeGameAdmin, fakeCategory);
        game.start();

        assertThat(game.isStarted()).isTrue();

    }

    @Test
    public void GivenCreatedGameWithGameAdmin_WhenNewPlayersAreJoining_ThenTheyShouldBeAddedToListOfGamePlayers() {

        Game game = arrangeGameOfAdminAndTwoPlayers();

        assertThat(game.getPlayers().get(0).equals(fakeGameAdmin));
        assertThat(game.getPlayers().get(1).equals(fakePlayer1));
        assertThat(game.getPlayers().get(2).equals(fakePlayer2));

    }

    @Test
    public void GivenListOf4Players_WhenPlayer2IsRemoved_ThenListShouldContain3PlayersAndPlayer2ShouldBeExcluded(){

        Game game = arrangeGameOfAdminAndTwoPlayers();
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
        Game game = arrangeGameOfAdminAndTwoPlayers();

        game.removePlayer(fakeGameAdmin);
        game.start();

        assertThat(game.isStarted()).isFalse();
    }


    private Game arrangeGameOfAdminAndTwoPlayers() {
        Game game = new Game(fakeGameAdmin, fakeCategory);

        game.addPlayer(fakePlayer1);
        game.addPlayer(fakePlayer2);
        return game;
    }
}
