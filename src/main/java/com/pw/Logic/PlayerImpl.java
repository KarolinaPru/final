package com.pw.Logic;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by Karolina on 20.03.2017.
 */

@Data
public class PlayerImpl implements Player {

    private String name;
    private static long nextAvailableId;
    @Getter
    private long id = 1;
    private int xp = 0;
    private int gamesPlayed;

    public PlayerImpl(String name) {
        this.name = name;
        id = nextAvailableId;
        nextAvailableId++;

    }

}
