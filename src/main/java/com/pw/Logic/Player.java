package com.pw.Logic;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;

/**
 * Created by Karolina on 20.03.2017.
 */

@Data
public class Player {

    private String name;
    private static int id;
    private int xp = 0;
    private int gamesPlayed;

    public Player(String name) {
        this.name = name;
        this.xp = xp;
        this.gamesPlayed = gamesPlayed;
        id++;

    }

    public static int getId() {
        return id;
    }


}
