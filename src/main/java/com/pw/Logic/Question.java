package com.pw.Logic;

import groovy.transform.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * Created by Karolina on 24.03.2017.
 */
@Getter
@EqualsAndHashCode
public class Question {
    private final Category category;
    private final String question;
    private final List<Answer> answers;

    public Question(String question, Category category, List<Answer> answers) {
        this.category = category;
        this.question = question;
        this.answers = answers;
    }
}

