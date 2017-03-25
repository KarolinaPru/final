package com.pw.Logic;

import lombok.Data;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

/**
 * Created by Karolina on 24.03.2017.
 */

@Data
public class Game {

    private boolean isActive;
    private ArrayList<PlayerInterface> players;
    private ArrayList<QuestionInterface> questions;

    //private Timer timer;



    public void startGame() {

        //TODO
        // player 0 jest adminem gry i moze ja rozpoczac

    }

    public ArrayList<QuestionInterface> getQuestions (ArrayList<Question> allQuestionsFromCategory) {
        Random random = new Random();
        Question q;

        if (allQuestionsFromCategory.size() >= 10) {

            for (int i = 0; i < 10; i++) {

                q = allQuestionsFromCategory.get(random.nextInt(allQuestionsFromCategory.size()));
                questions.add(q);

            }

        }

        return questions;
    }



}
