package com.pw.Logic;

import javafx.util.Pair;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Karolina on 26.03.2017.
 */
public class QuestionServiceHardcoded implements QuestionService {

    private Random random = new Random();

    @Override
    public List<Question> get10RandomQuestions(Category category) {
        List<Question> questions = new ArrayList<>();


        List<Question> allQuestions = createQuestions(Category.MISCELLANEOUS);
        int size = allQuestions.size();

        Question q;

        for (int i = 0; i < 10; i++) {
            q = allQuestions.get(random.nextInt(size));
            if(!questions.contains(q)) {
                questions.add(q);
            } else {
                i--;
            }
        }

        return questions;
    }

    private List<Question> createQuestions(Category category) {
        List<Question> allQuestions = new ArrayList<>();

        Question question1 = QuestionImpl.builder()
                .question("Question 1")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", false))
                .answer2(new Pair<>("2", true))
                .answer3(new Pair<>("3", false))
                .answer4(new Pair<>("4", false))
                .build();

        Question question2 = QuestionImpl.builder()
                .question("Question 2")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", false))
                .answer2(new Pair<>("2", false))
                .answer3(new Pair<>("3", true))
                .answer4(new Pair<>("4", false))
                .build();

        Question question3 = QuestionImpl.builder()
                .question("Question 3")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", false))
                .answer2(new Pair<>("2", false))
                .answer3(new Pair<>("3", false))
                .answer4(new Pair<>("4", true))
                .build();

        Question question4 = QuestionImpl.builder()
                .question("Question 4")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", false))
                .answer2(new Pair<>("2", false))
                .answer3(new Pair<>("3", true))
                .answer4(new Pair<>("4", false))
                .build();

        Question question5 = QuestionImpl.builder()
                .question("Question 5")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", true))
                .answer2(new Pair<>("2", false))
                .answer3(new Pair<>("3", false))
                .answer4(new Pair<>("4", false))
                .build();

        Question question6 = QuestionImpl.builder()
                .question("Question 6")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", false))
                .answer2(new Pair<>("2", false))
                .answer3(new Pair<>("3", true))
                .answer4(new Pair<>("4", false))
                .build();

        Question question7 = QuestionImpl.builder()
                .question("Question 7")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", false))
                .answer2(new Pair<>("2", true))
                .answer3(new Pair<>("3", false))
                .answer4(new Pair<>("4", false))
                .build();

        Question question8 = QuestionImpl.builder()
                .question("Question 8")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", true))
                .answer2(new Pair<>("2", false))
                .answer3(new Pair<>("3", false))
                .answer4(new Pair<>("4", false))
                .build();

        Question question9 = QuestionImpl.builder()
                .question("Question 9")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", true))
                .answer2(new Pair<>("2", false))
                .answer3(new Pair<>("3", false))
                .answer4(new Pair<>("4", false))
                .build();

        Question question10 = QuestionImpl.builder()
                .question("Question 10")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("1", false))
                .answer2(new Pair<>("2", false))
                .answer3(new Pair<>("3", true))
                .answer4(new Pair<>("4", false))
                .build();

        Question question11 = QuestionImpl.builder()
                .question("Question 11")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("a", false))
                .answer2(new Pair<>("b", true))
                .answer3(new Pair<>("c", false))
                .answer4(new Pair<>("d", false))
                .build();

        Question question12 = QuestionImpl.builder()
                .question("Question 12")
                .category(Category.MISCELLANEOUS)
                .answer1(new Pair<>("a", true))
                .answer2(new Pair<>("b", false))
                .answer3(new Pair<>("c", false))
                .answer4(new Pair<>("d", false))
                .build();

        allQuestions.add(question1);
        allQuestions.add(question2);
        allQuestions.add(question3);
        allQuestions.add(question4);
        allQuestions.add(question5);
        allQuestions.add(question6);
        allQuestions.add(question7);
        allQuestions.add(question8);
        allQuestions.add(question9);
        allQuestions.add(question10);
        allQuestions.add(question11);
        allQuestions.add(question12);

        return allQuestions;
    }

}