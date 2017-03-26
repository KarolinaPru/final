package com.pw.Logic;

import lombok.Data;
import lombok.NonNull;

import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */

@Data
public class GameImpl {

    @NonNull
    private Player gameAdmin;
    @NonNull
    Category category;
    private ArrayList<Player> players = new ArrayList<>();
    private Map<Integer, QuestionInterface> questions;
    private boolean isStarted;


    public GameImpl(Player gameAdmin, Category category) {
        if(gameAdmin == null || category == null) {
            throw new IllegalArgumentException("The game cannot be initiated without the game admin and a category.");
        }
        this.gameAdmin = gameAdmin;
        this.category = category;

        players.add(gameAdmin);

    }

    public ArrayList<Player> addPlayer(Player player) {
        players.add(player);

        return players;
    }

    public ArrayList<Player> removePlayer(Player player) {
        players.remove(player);

        return players;
    }


    public void start() {

        if(players.contains(gameAdmin)) {

//            if(!questions.isEmpty()) {
//                isStarted = true;
//            } else {
//                throw new IllegalArgumentException("There are not enough questions in this category.  Please choose another one or add them.");
//            }
        }

        isStarted = false;
    }

//    public ArrayList<QuestionInterface> getRandomQuestions() {
//        ArrayList<QuestionInterface> allQuestionsFromCategory = category.getAllQuestionsFromCategory();
//
//        Random random = new Random();
//        QuestionInterface question;
//
//        if (allQuestionsFromCategory.size() >= 10) {
//
//            for (int i = 0; i < 10; i++) {
//
//                question = allQuestionsFromCategory.get(random.nextInt(allQuestionsFromCategory.size()));
//                questions.add(question);
//
//                }
//            }
//
//            return questions;
//    }

}
