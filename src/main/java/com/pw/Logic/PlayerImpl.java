package com.pw.Logic;

import lombok.Data;

import java.util.UUID;

/**
 * Created by Karolina on 20.03.2017.
 */

@Data
public class PlayerImpl implements Player {

    private String name;
    private UUID id;
    private int xp = 0;
    private int gamesPlayed;

    public PlayerImpl(String name) {
        this.name = name;
        id = UUID.randomUUID();

    }

}
