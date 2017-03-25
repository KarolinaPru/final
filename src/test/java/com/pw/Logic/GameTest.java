package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Created by Karolina on 25.03.2017.
 */
public class GameTest {
    private ArrayList<QuestionInterface> noQuestions;
    private ArrayList<PlayerInterface> noPlayers;

    @Before
    public void setup() {
        noQuestions = new ArrayList<>();
        noPlayers = new ArrayList<>();
    }

    @Test
    public void GivenPlayersAndQuestions_WhenGameInstatiated_ThenItShouldBeCreated() {

        Game gameOne = new Game(noPlayers, noQuestions);
        assertThat(gameOne).isNotNull();

    }

    @Test
    public void GivenAtLeastOnePlayer_WhenStartingGame_ThenItShouldBeStarted(){
        ArrayList<PlayerInterface> onePlayer = new ArrayList<>();
        onePlayer.add(new Player("Player 1"));

        Game game = new Game(onePlayer, noQuestions);
        game.start();

        assertThat(game.isStarted).isTrue();

    }

    @Test
    public void GivenNoPlayers_WhenStartingGame_ThenItShouldNotBeStarted(){

        Game game = new Game(noPlayers, noQuestions);
        game.start();

        assertThat(game.isStarted).isFalse();

    }

}