package com.pw.Logic;

import lombok.Getter;

/**
 * Created by Karolina on 26.03.2017.
 */
@Getter
public class Category {
    private final String name;
    private final String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
