package com.pw.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Karolina on 26.03.2017.
 */
public class QuestionServiceHardcoded implements QuestionService {

    private Random random = new Random();
    private Category category = new CategoryImpl("Category1", "some category");

    @Override
    public List<Question> get10RandomQuestions(Category category) {
        List<Question> questions = new ArrayList<>();

        List<Question> allQuestions = createQuestions(category);
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
        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new AnswerImpl("1", false);
        Answer answer2 = new AnswerImpl("2", true);
        Answer answer3 = new AnswerImpl("3", false);
        Answer answer4 = new AnswerImpl("4", false);

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);


        Question question1 = QuestionImpl.builder()
                .question("Question 1")
                .category(category)
                .answers(answers)
                .build();

        Question question2 = QuestionImpl.builder()
                .question("Question 2")
                .category(category)
                .answers(answers)
                .build();

        Question question3 = QuestionImpl.builder()
                .question("Question 3")
                .category(category)
                .answers(answers)
                .build();

        Question question4 = QuestionImpl.builder()
                .question("Question 4")
                .category(category)
                .answers(answers)
                .build();

        Question question5 = QuestionImpl.builder()
                .question("Question 5")
                .category(category)
                .answers(answers)
                .build();

        Question question6 = QuestionImpl.builder()
                .question("Question 6")
                .category(category)
                .answers(answers)
                .build();

        Question question7 = QuestionImpl.builder()
                .question("Question 7")
                .category(category)
                .answers(answers)
                .build();

        Question question8 = QuestionImpl.builder()
                .question("Question 8")
                .category(category)
                .answers(answers)
                .build();

        Question question9 = QuestionImpl.builder()
                .question("Question 9")
                .category(category)
                .answers(answers)
                .build();

        Question question10 = QuestionImpl.builder()
                .question("Question 10")
                .category(category)
                .answers(answers)
                .build();

        Question question11 = QuestionImpl.builder()
                .question("Question 11")
                .category(category)
                .answers(answers)
                .build();

        Question question12 = QuestionImpl.builder()
                .question("Question 12")
                .category(category)
                .answers(answers)
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