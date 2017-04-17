package com.pw.Logic;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pw.Logic.Exceptions.IllegalNumberOfQuestionsException;
import com.pw.Logic.Exceptions.ScoreCannotBeRetrievedBeforeGameIsClosedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Created by Karolina on 25.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    private Category category;
    private List<Question> questions;
    private GameStateMachine gameStateMachine;

    //    private Player gameOwner = new Player("Zenek");
//    private Player player1 = new Player("Janek");
//    @Mock
//    private Player player2;
//    @Mock
//    private Player player3;
//    @Mock
//    private List<Question> questions;
//    @Mock
//    private Category category;
//    private ArrayList<Answer> submittedAnswers;
//    private Map<Player, Integer> scores;
//
    @Test
    public void givenCategoryQuestionsAndNotClosedGameStatus_WhenGetScoresIsCalled_ThenScoresAreNotNull() throws IllegalNumberOfQuestionsException, ScoreCannotBeRetrievedBeforeGameIsClosedException {
        // http://www.vogella.com/tutorials/Mockito/article.html#mock-object-generation

        givenMockedCategoryQuestionsAndGameStateMachine();
        given10Questions();
        Game game = givenGameWithCategoryQuestionsAndStateMachine();
        givenGameStateIsClosed();

        List<Score> scores = game.getScores();

        assertThat(scores).isNotNull();
    }

    @Test
    public void givenGameStateIsClosed_WhenGetScoresIsCalled_ThenExceptionIsThrown() throws ScoreCannotBeRetrievedBeforeGameIsClosedException, IllegalNumberOfQuestionsException {
        givenMockedCategoryQuestionsAndGameStateMachine();
        given10Questions();
        Game game = givenGameWithCategoryQuestionsAndStateMachine();
        givenGameStateIsNotClosed();

        assertThatThrownBy(() -> game.getScores()).isInstanceOf(ScoreCannotBeRetrievedBeforeGameIsClosedException.class);

    }


    private Game givenGameWithCategoryQuestionsAndStateMachine() throws IllegalNumberOfQuestionsException {
        return new Game(category, questions, gameStateMachine);
    }

    private void
    givenGameStateIsNotClosed() {
        when(gameStateMachine.gameIsClosed()).thenReturn(false);
    }

    private void givenGameStateIsClosed() {
        when(gameStateMachine.gameIsClosed()).thenReturn(true);
    }

    private void given10Questions() {
        when(questions.size()).thenReturn(10);
    }

    private void givenMockedCategoryQuestionsAndGameStateMachine() {
        category = mock(Category.class);
        questions = mock(List.class);
        gameStateMachine = mock(GameStateMachine.class);
    }

//    @Test
//    public void GivenPlayerAndCategory_WhenInstantiatingGame_ThenItShouldNotBeNull() {
//
//        Game gameOne = new Game(gameOwner, category, questions);
//        assertThat(gameOwner).isEqualTo(gameOne.getGameOwner());
//        assertThat(category).isEqualTo(gameOne.getCategory());
//        assertThat(gameOne).isNotNull();
//    }
//
//    @Test
//    public void GivenCreatedGameWithGameOwner_WhenNewPlayersAreJoining_ThenTheyShouldBeAddedToListOfGamePlayers() {
//
//        Game game = arrangeGameOfOwnerAndTwoPlayers();
//
//        assertThat(game.getPlayers().get(0).equals(game.getGameOwner()));
//        assertThat(game.getPlayers().get(1).equals(player1));
//        assertThat(game.getPlayers().get(2).equals(player2));
//    }
//
//    @Test
//    public void GivenOwnerAndCategory_WhenCreatingNewGame_ThenIdShouldBeAssignedAndNotBeNull() {
//        Game game = arrangeGameOfOwnerAndTwoPlayers();
//
//        assertThat(game).hasFieldOrProperty("id");
//        assertThat(game.getId()).isNotNull();
//        assertThat(game.getId()).isLessThan(game.getNextAvailableId());
//    }
//
//    @Test
//    public void GivenGameIsCreated_WhenCheckedForGameStateCreated_ThenTrueShouldBeReturned() {
//        Game game = arrangeGameOfOwnerAndTwoPlayers();
//
//        assertThat(game.getCurrentState() == GameState.OPEN).isTrue();
//    }
//
//    @Test
//    public void GivenListOf4Players_WhenPlayer2IsRemoved_ThenListShouldContain3PlayersAndPlayer2ShouldBeExcluded() {
//
//        Game game = arrangeGameOfOwnerAndTwoPlayers();
//        game.addPlayer(player3);
//        int playersBeforeRemoval = game.getPlayers().size();
//
//        game.removePlayer(player2);
//        int playersAfterRemoval = game.getPlayers().size();
//
//        assertThat(playersBeforeRemoval).isEqualTo(4);
//        assertThat(playersAfterRemoval).isEqualTo(3);
//        assertThat(game.getPlayers()).doesNotContain(player2);
//    }
//
//    @Test
//    public void GivenGameWithoutOwner_WhenStarting_ThenGameShouldNotStart() {
//        Game game = arrangeGameOfOwnerAndTwoPlayers();
//
//        game.removePlayer(gameOwner);
//        game.start();
//
//        assertThat(game.getCurrentState() == GameState.STARTED).isFalse();
//    }
//
//    @Test
//    public void GivenGameOwnerAnd10QuestionsInCategory_WhenStartingGame_ThenItShouldStart() {
//
//        Game game = arrangePositiveGameConditions();
//
//        game.start();
//
//        assertThat(game.getCurrentState() == GameState.STARTED).isTrue();
//    }
//
//    @Test
//    public void GivenGameConditionsAreMet_WhenStarting_ThenStartTimeShouldBeAssigned()  {
//        Game game = arrangePositiveGameConditions();
//
//        game.start();
//
//        assertThat(game).hasFieldOrProperty("startTime");
//        assertThat(game.getStartTime()).isNotNull();
//    }
//
//    @Test
//    public void GivenPlayer_WhenEndingGame_ThenPlayerShouldBeRemoved() {
//        Game game = arrangePositiveGameConditions();
//        game.start();
//
//        game.end(player1);
//
//        assertThat(game.getPlayers()).doesNotContain(player1);
//    }
//
//    @Test
//    public void GivenAllPlayersEndedGame_WhenCheckingIfIsCalculatingScore_ThenFalseShouldBeReturned() {
//        Game game = arrangePositiveGameConditions();
//        game.start();
//
//        game.end(player1);
//        game.end(player2);
//        game.end(gameOwner);
//
//        assertThat(game.getCurrentState() == GameState.EVALUATING_ANSWERS).isFalse();
//    }
//
//    @Test
//    public void GivenNotAllPlayersEndedGame_WhenCheckingIfIsCalculatingScore_ThenTrueShouldBeReturned() {
//        Game game = arrangePositiveGameConditions();
//        game.start();
//
//        game.end(player1);
//        game.end(gameOwner);
//
//        assertThat(game.getCurrentState() == GameState.EVALUATING_ANSWERS).isTrue();
//    }
//
//    @Test
//    public void GivenGameWasStarted_WhenEvaluatingUsersAnswers_ThenCorrectScoreShouldBeReturned() {
//        Game game = arrangePositiveGameConditions();
//        startGameAndGetSubmittedAnswers(game);
//
//        int expectedScore = 60;
//        int actualScore = game.evaluateAnswers(player1, submittedAnswers);
//
//        assertThat(actualScore).isEqualTo(expectedScore);
//    }
//
//    @Test
//    public void GivenEvaluatingAnswers_WhenCalled_ThenPlayerShouldReceiveXP() {
//        Game game = arrangePositiveGameConditions();
//        startGameAndGetSubmittedAnswers(game);
//
//        int initialXp = player1.getXp();
//        int score = game.evaluateAnswers(player1, submittedAnswers);
//
//        int expectedXp = initialXp + score;
//
//        assertThat(player1.getXp()).isEqualTo(expectedXp);
//    }
//
//    @Test
//    public void GivenEvaluatingAnswers_WhenCalled_ThenPlayersGamePlayedShouldBeUpdated() {
//
//        int initialGamesPlayed = player1.getGamesPlayed();
//        Game game = arrangePositiveGameConditions();
//        startGameAndGetSubmittedAnswers(game);
//
//        game.evaluateAnswers(player1, submittedAnswers);
//
//        Game game2 = arrangePositiveGameConditions();
//        startGameAndGetSubmittedAnswers(game2);
//
//        game2.evaluateAnswers(player1, submittedAnswers);
//
//        int expectedGamesPlayed = initialGamesPlayed + 2;
//
//        assertThat(player1.getGamesPlayed()).isEqualTo(expectedGamesPlayed);
//
//    }
//
//    @Test
//    public void GivenAtLeast2Players_WhenDeterminingWinner_ThenOneWithTheHighestScoreShouldBeReturned() {
//        Game game = arrangePositiveGameConditions();
//        startGameAndGetSubmittedAnswers(game);
//        arrangeMapOfPlayersScores(game);
//
//        Player actualWinner = game.determineWinner(scores);
//
//        assertThat(actualWinner).isEqualTo(player1);
//    }
//
//    @Test
//    public void GivenAtLeast2Players_WhenDeterminingWinner_ThenTheyReceiveBonusOf30Xp() {
//        Game game = arrangePositiveGameConditions();
//        startGameAndGetSubmittedAnswers(game);
//        arrangeMapOfPlayersScores(game);
//
//        int winnersXpAfterEvaluation = player1.getXp();
//        game.determineWinner(scores);
//
//        assertThat(player1.getXp()).isEqualTo(winnersXpAfterEvaluation + 30);
//    }
//
//    @Test
//    public void GivenSinglePlayer_WhenDeterminingWinner_ThenNoBonusIsGiven() {
//        Game game = new Game(gameOwner, category, questions);
//        startGameAndGetSubmittedAnswers(game);
//        game.evaluateAnswers(gameOwner, submittedAnswers);
//
//        int xpAfterEvaluation = gameOwner.getXp();
//        game.determineWinner(game.getScores());
//
//        assertThat(gameOwner.getXp()).isEqualTo(xpAfterEvaluation);
//        assertThat(game.getWinner().equals(gameOwner));
//    }
//
//    @Test
//    public void GivenScoresAreNull_WhenDeterminingWinner_ThenExceptionIsThrown() {
//        Game game = new Game(gameOwner, category, questions);
//        startGameAndGetSubmittedAnswers(game);
//
//        assertThatThrownBy(() -> game.determineWinner(null)).isInstanceOf(IllegalArgumentException.class);
//    }
//
//    private void arrangeMapOfPlayersScores(Game game) {
//        int scorePlayer1 = game.evaluateAnswers(player1, submittedAnswers);
//        int scoreGameOwner = game.evaluateAnswers(gameOwner, getSubmittedAnswers2());
//
//        scores = new HashMap<>();
//        scores.put(player2, 0);
//        scores.put(player1, scorePlayer1);
//        scores.put(gameOwner, scoreGameOwner);
//    }
//
//    private void startGameAndGetSubmittedAnswers(Game game) {
//        game.start();
//        submittedAnswers = getSubmittedAnswers();
//    }
//
//
//    private ArrayList<Answer> getSubmittedAnswers() {
//        ArrayList<Answer> submittedAnswers = new ArrayList<>();
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", false));
//        submittedAnswers.add(new Answer("", false));
//        submittedAnswers.add(new Answer("", false));
//        submittedAnswers.add(new Answer("", false));
//        return submittedAnswers;
//    }
//
//    private ArrayList<Answer> getSubmittedAnswers2() {
//        ArrayList<Answer> submittedAnswers = new ArrayList<>();
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", true));
//        submittedAnswers.add(new Answer("", false));
//        submittedAnswers.add(new Answer("", false));
//        submittedAnswers.add(new Answer("", false));
//        submittedAnswers.add(new Answer("", false));
//        submittedAnswers.add(new Answer("", false));
//        return submittedAnswers;
//    }
//
//    private Game arrangePositiveGameConditions() {
//        Mockito.when(questions.size()).thenReturn(10);
//        return arrangeGameOfOwnerAndTwoPlayers();
//    }
//
//    private Game arrangeGameOfOwnerAndTwoPlayers() {
//        Game game = new Game(gameOwner, category, questions);
//
//        game.addPlayer(player1);
//        game.addPlayer(player2);
//        return game;
//    }
}
