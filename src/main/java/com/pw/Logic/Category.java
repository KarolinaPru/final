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
    private ArrayList<QuestionInterface> randomQuestions;

    public Category (String category) {
        this.category = category;
    }


    public ArrayList<QuestionInterface> getRandomQuestions(ArrayList<Question> allQuestionsFromCategory) {
        Random random = new Random();
        Question question;

        if (allQuestionsFromCategory.size() >= 10) {

            for (int i = 0; i < 10; i++) {

                question = allQuestionsFromCategory.get(random.nextInt(allQuestionsFromCategory.size()));
                randomQuestions.add(question);

            }

        }

        return randomQuestions;
    }
}
