package com.pw.Logic;

import lombok.Data;
import lombok.NonNull;

import java.util.*;

/**
 * Created by Karolina on 24.03.2017.
 */

@Data
public class Game {

    private boolean isActive;
    @NonNull
    private PlayerInterface gameAdmin;
    @NonNull
    Category category;
    private ArrayList<PlayerInterface> players = new ArrayList<>();
    private ArrayList<QuestionInterface> questions;
    private boolean isStarted;


    public Game (PlayerInterface gameAdmin, Category category) {
        if(gameAdmin == null || category == null) {
            throw new IllegalArgumentException("The game cannot be initiated without the game admin and a category.");
        }
        this.gameAdmin = gameAdmin;
        this.category = category;
        players.add(gameAdmin);

    }



    public void start() {


        isStarted = true;
    }


    public ArrayList<PlayerInterface> addPlayer(PlayerInterface player) {
        players.add(player);

        return players;
    }
}
