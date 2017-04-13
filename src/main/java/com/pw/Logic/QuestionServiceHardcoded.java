package com.pw.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Karolina on 26.03.2017.
 */
public class QuestionServiceHardcoded implements QuestionService {

    private Random random = new Random();
    private Category category = new Category("Category1", "some category");

    @Override
    public List<Question> get10RandomQuestions(Category category) {
        List<Question> questions = new ArrayList<>();

        List<Question> allQuestions = createQuestions(category);
        int size = allQuestions.size();

        Question q;

        if (size >= 10) {
            for (int i = 0; i < 10; i++) {
                q = allQuestions.get(random.nextInt(size));
                if (!questions.contains(q)) {
                    questions.add(q);
                } else {
                    i--;
                }
            }
        }
        return questions;
    }

    protected List<Question> createQuestions(Category category) {
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

        Question question1 = new QuestionImpl("Question 1", category, answers);
        Question question2 = new QuestionImpl("Question 2", category, answers);
        Question question3 = new QuestionImpl("Question 3", category, answers);
        Question question4 = new QuestionImpl("Question 4", category, answers);
        Question question5 = new QuestionImpl("Question 5", category, answers);
        Question question6 = new QuestionImpl("Question 6", category, answers);
        Question question7 = new QuestionImpl("Question 7", category, answers);
        Question question8 = new QuestionImpl("Question 8", category, answers);
        Question question9 = new QuestionImpl("Question 9", category, answers);
        Question question10 = new QuestionImpl("Question 10", category, answers);
        Question question11 = new QuestionImpl("Question 11", category, answers);
        Question question12 = new QuestionImpl("Question 12", category, answers);

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