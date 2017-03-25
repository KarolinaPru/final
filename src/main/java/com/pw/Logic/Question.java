package com.pw.Logic;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Karolina on 24.03.2017.
 */
@Data
@Builder
public class Question implements QuestionInterface {
    private int id;
    private Category category;
    private String question;
    private ArrayList<Answer> answerOptions;


    public Question(Category category, String question, ArrayList<Answer> answerOptions) {
        this.category = category;
        this.question = question;
        this.answerOptions = answerOptions;
    }


}

