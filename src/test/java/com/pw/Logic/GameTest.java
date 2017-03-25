package com.pw.Logic;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Karolina on 25.03.2017.
 */
public class GameTest {



    @Test
    public void getQuestions_GivenAListOfQuestions_WhenCalled_Then10RandomAreReturned() {

        Category category1 = new Category("Category 1");

       ArrayList<Answer> answers1 = new ArrayList<>();
       answers1.add(new Answer("A", true));
       answers1.add(new Answer("B", false));
       answers1.add(new Answer("C", false));
       answers1.add(new Answer("D", false));


       Question question1 = Question.builder()
               .category(category1)
               .question("Question 1")
               .answerOptions(answers1)
               .id(1)
               .build();

       Question question2 = Question.builder()
               .category(category1)
               .question("Question 2")
               .answerOptions(answers1)
               .id(2)
               .build();

       Question question3 = Question.builder()
                .category(category1)
                .question("Question 3")
                .answerOptions(answers1)
                .id(3)
                .build();

       Question question4 = Question.builder()
                .category(category1)
                .question("Question 4")
                .answerOptions(answers1)
                .id(4)
                .build();


        ArrayList<QuestionInterface> allQuestions = new ArrayList<>();
        allQuestions.add(question1);
        allQuestions.add(question2);
        allQuestions.add(question3);
        allQuestions.add(question4);



    }

}