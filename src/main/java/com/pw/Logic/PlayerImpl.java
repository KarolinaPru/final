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
    private static long nextAvailableId = 1;
    private long id;
    private int xp;
    private int gamesPlayed;

    public PlayerImpl(String name) {
        this.name = name;
        id = nextAvailableId;
        nextAvailableId++;

    }

    @Override
    public int getXp() {
        return xp;
    }

    @Override
    public void setXp(int xp) {
        this.xp = xp;
    }

    @Override
    public int getGamesPlayed(){
        return gamesPlayed;
    }

    @Override
    public void incrementGamesPlayed() {
        this.gamesPlayed += 1;
    }
}
