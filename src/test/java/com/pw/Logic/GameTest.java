package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

/**
 * Created by Karolina on 25.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private PlayerInterface fakeGameAdmin;
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

        Game game = new Game(fakeGameAdmin, fakeCategory);
        PlayerInterface player2 = new Player("Player 2");
        PlayerInterface player3 = new Player("Player 3");

        game.addPlayer(player2);
        game.addPlayer(player3);

        assertThat(game.getPlayers().get(0).equals(fakeGameAdmin));
        assertThat(game.getPlayers().get(1).equals(player2));
        assertThat(game.getPlayers().get(2).equals(player3));

    }



}