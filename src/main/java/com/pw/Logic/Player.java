package com.pw.Logic;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Karolina on 20.03.2017.
 */
@EqualsAndHashCode
public class Player {
    @Getter
    private final String name;
    @Getter
    private int xp;
    @Getter
    private int gamesPlayed;

    public Player(String name) {
        this.name = name;
    }

    public int getXp() {
        return xp;
    }

    public int getGamesPlayed(){
        return gamesPlayed;
    }

    protected void addXp(int xp) {
        this.xp += xp;
    }

    protected void incrementGamesPlayed() {
        this.gamesPlayed += 1;
    }
}
