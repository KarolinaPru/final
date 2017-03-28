package com.pw.Logic;

import groovy.transform.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Karolina on 24.03.2017.
 */

@Getter
@EqualsAndHashCode
public class QuestionImpl implements Question {
    private Category category;
    private String question;
    private List<Answer> answers;
    private UUID id = UUID.randomUUID();


    public QuestionImpl(String question, Category category, List<Answer> answers) {
        this.category = category;
        this.question = question;
        this.answers = answers;
        id = UUID.randomUUID();
    }

    @Override
    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public List<UUID> getUuidOfCorrectAnswers(List<Answer> answers) {
        this.answers = answers;

        List<UUID> correctAnswersUuids = new ArrayList<>();

        for (Answer answer : answers) {

            if(answer.isCorrect()) {
                correctAnswersUuids.add(answer.getId());
            }
        }

        return correctAnswersUuids;
    }
}

