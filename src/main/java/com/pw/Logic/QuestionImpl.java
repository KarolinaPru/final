package com.pw.Logic;

import groovy.transform.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by Karolina on 24.03.2017.
 */
@Getter
@EqualsAndHashCode
@Builder
public class QuestionImpl implements Question {
    private Category category;
    private String question;
    private List<Answer> answers;


    public QuestionImpl(Category category, String question, List<Answer> answers) {
        this.category = category;
        this.question = question;
        this.answers = answers;
    }


}

