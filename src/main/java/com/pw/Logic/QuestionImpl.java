package com.pw.Logic;

import javafx.util.Pair;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Karolina on 24.03.2017.
 */
@Data
public class QuestionImpl implements QuestionInterface {
    private Category category;
    private String question;
    private Pair<String, Boolean> answer1;
    private Pair<String, Boolean> answer2;
    private Pair<String, Boolean> answer3;
    private Pair<String, Boolean> answer4;


    public QuestionImpl(Category category, String question,
                        Pair<String, Boolean> answer1, Pair<String, Boolean> answer2,
                        Pair<String, Boolean> answer3, Pair<String, Boolean> answer4) {
        this.category = category;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }


}

