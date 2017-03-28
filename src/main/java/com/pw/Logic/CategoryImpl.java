package com.pw.Logic;

import java.util.UUID;

/**
 * Created by Karolina on 26.03.2017.
 */
public class CategoryImpl implements Category {
    private String name;
    private String description;
    private UUID id;

    CategoryImpl(String name, String description) {
        this.name = name;
        this.description = description;
        id = UUID.randomUUID();
    }

}
