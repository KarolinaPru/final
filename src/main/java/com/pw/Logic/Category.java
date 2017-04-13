package com.pw.Logic;

import lombok.Getter;

/**
 * Created by Karolina on 26.03.2017.
 */
public class Category {
    private String name;
    private String description;
    private static long nextAvailableId;
    @Getter
    private long id = 1;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        id = nextAvailableId;
        nextAvailableId++;
    }

}
