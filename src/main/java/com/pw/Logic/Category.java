package com.pw.Logic;

import lombok.Data;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Karolina on 24.03.2017.
 */
@Data
public class Category {
    private String category;
    private ArrayList<QuestionInterface> allQuestionsFromCategory;
    private ArrayList<QuestionInterface> randomQuestions;

    public Category (String category) {
        this.category = category;
    }

    private ArrayList<QuestionInterface> addQuestionsToCategory() {
        ArrayList<AnswerInterface> answers = new ArrayList<>();
        answers.add(new Answer("1", false));
        answers.add(new Answer("2", true));
        answers.add(new Answer("3", false));
        answers.add(new Answer("4", false));

        for (int i = 0; i < 100; i++) {
           QuestionInterface question = new Question(this, "question" + i, answers);
           allQuestionsFromCategory.add(question);

        }

        return allQuestionsFromCategory;
    }


}
