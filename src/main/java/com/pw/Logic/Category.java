package com.pw.Logic;

/**
 * Created by Karolina on 26.03.2017.
 */
public enum Category {
    BIOLOGY ("Biology"),
    HISTORY ("History"),
    SCIENCE ("Science"),
    TECHNOLOGY("Technology"),
    ARTS ("Arts"),
    CODING ("Coding"),
    LANGUAGE ("Language"),
    MISCELLANEOUS ("Miscellaneous");


    Category(String name) {
        this.name = name;
    }

    private String name;
}
