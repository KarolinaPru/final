package com.pw.Logic;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Karolina on 24.03.2017.
 */
@Data
public class Category {
    private String category;
    private int id;

    public Category (String category) {
        this.category = category;
    }
}
