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
    private PlayerInterface fakePlayer1;
    @Mock
    private PlayerInterface fakePlayer2;
    @Mock
    private PlayerInterface fakePlayer3;
    @Mock
    private Category fakeCategory;
    @Mock
    private ArrayList<QuestionInterface> fakeQuestions;

    @InjectMocks
    private Game game;


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

    @Test
    public void GivenCategoryWithNoQuestions_WhenStarting_ThenIllegalArgumentExceptionShouldBeThrown() {
        Game game = arrangeGameOfAdminAndTwoPlayers();

        assertThatThrownBy(() -> game.start())
                .isInstanceOf(IllegalArgumentException.class);


    }

//    @Test
//    public void GivenGameAdminAndQuestionsInCategory_WhenStartingGame_ThenItShouldBeStarted(){
//
//        Game game = new Game(fakeGameAdmin, fakeCategory);
//        game.setQuestions(fakeQuestions);
//        game.start();
//
//        assertThat(game.getQuestions()).isNotEmpty();
//        assertThat(game.isStarted()).isTrue();
//
//    }


    private Game arrangeGameOfAdminAndTwoPlayers() {
        Game game = new Game(fakeGameAdmin, fakeCategory);

        game.addPlayer(fakePlayer1);
        game.addPlayer(fakePlayer2);
        return game;
    }
}
