package com.pw.Logic;

import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Karolina on 28.03.2017.
 */
public class QuestionImplTest {

    private Category category = new CategoryImpl("Cat1", "description");
    private List<Question> questions = new ArrayList<>();
    private List<Answer> answers= new ArrayList<>();

    @Test
    public void GivenQuestion_WhenGetUUIsOfCorrectAnswersIsCalled_ThenListShouldContainAppropriateValues() {

        Answer answer1 = new AnswerImpl("1", true);
        Answer answer2 = new AnswerImpl("2", false);
        Answer answer3 = new AnswerImpl("3", true);
        Answer answer4 = new AnswerImpl("4", false);
        Answer answer5 = new AnswerImpl("5", true);

        UUID id1 = answer1.getId();
        UUID id2 = answer2.getId();
        UUID id3 = answer3.getId();
        UUID id4 = answer4.getId();
        UUID id5 = answer5.getId();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer5);

        List<UUID> uuids = new ArrayList<>();

        QuestionImpl qi = new QuestionImpl("Question1", category, answers);

        uuids = qi.getUuidOfCorrectAnswers(answers);

        assertThat(uuids).contains(id1, id3, id5);
        assertThat(uuids).doesNotContain(id2, id4);
    }
}