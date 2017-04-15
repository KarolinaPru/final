package com.pw.Logic;

import com.pw.Logic.Exceptions.IllegalNumberOfQuestionsException;
import com.pw.Logic.Exceptions.IllegalTimeOfAnswerSubmissionException;
import com.pw.Logic.Exceptions.ScoreCannotBeRetrievedBeforeGameIsClosedException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Karolina on 14.04.2017.
 */
public class IntegrationTest {

    @Test
    public void GivenSeveralPlayers_WhenEveryoneSubmitsTheirAnswers_ThenWinnerIsDeterminedAndGivenBonus() throws IllegalNumberOfQuestionsException, IllegalTimeOfAnswerSubmissionException, ScoreCannotBeRetrievedBeforeGameIsClosedException {
        // First API call (Get categories)

        // Second API call (Create a game)
        Category testCategory = new Category("Test", "Test");
        QuestionService service = new QuestionServiceHardcoded(); // should get questions for a given category
        Game game = new Game(testCategory, service.get10RandomQuestions(testCategory));
        PlayerInGame playerOne = new PlayerInGame("Player 1", game, true);

        // Third API call (Player joins)
        PlayerInGame playerTwo = new PlayerInGame("Player 2", game, false);

        // Fourth API call (Player joins)
        PlayerInGame playerThree = new PlayerInGame("Player 3", game, false);

        // Fifth API call (Owner starts the game)
        playerOne.startGame();

        // Sixth API call (Answers submission)
        playerOne.submitAnswers(getCorrectAnswers(5));
        // Seventh API call
        playerTwo.submitAnswers(getCorrectAnswers(6));
        // Eighth API call
        playerThree.submitAnswers(getCorrectAnswers(3));

        // Ninth, Tenth, Eleventh API call (Each player wants to see the scores)
        List<Score> scores = game.getScores();

        Player actualWinner = scores.stream()
                .sorted(Comparator.comparingInt(Score::getPoints).reversed())
                .findFirst()
                .get()
                .getPlayer();

        assertThat(actualWinner).isEqualTo(playerTwo);
        assertThat(actualWinner.getXp()).isEqualTo(6 * 10 + 30); // 6 correct answers and a bonus
    }

    private ArrayList<Answer> getCorrectAnswers(int numberOfCorrectAnswers) {
        ArrayList<Answer> answers = new ArrayList<>();

        for (int i = 0; i < numberOfCorrectAnswers; i++) {
            answers.add(new Answer("", true));
        }

        return answers;
    }
}
