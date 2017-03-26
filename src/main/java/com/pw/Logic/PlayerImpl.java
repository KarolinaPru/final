package com.pw.Logic;

import lombok.Data;

/**
 * Created by Karolina on 20.03.2017.
 */

@Data
public class PlayerImpl implements Player {

    private String name;
    private static int id;
    private int xp = 0;
    private int gamesPlayed;

    public PlayerImpl(String name) {
        this.name = name;
        this.xp = xp;
        this.gamesPlayed = gamesPlayed;
        id++;

    }

    public static int getId() {
        return id;
    }


}
