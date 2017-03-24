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

    private Category category;
    private ArrayList<Player> players;
    private ArrayList<Question> questions;

    private Timer timer;



    public void startGame() {


    }

    private ArrayList<Question> getQuestions (ArrayList<Question> allQuestionsFromCategory) {
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
