package com.pw.Logic;

import lombok.Data;

/**
 * Created by Karolina on 25.03.2017.
 */
@Data
public class AnswerImpl implements Answer {
    private String answer;
    private boolean isCorrect;

    public AnswerImpl(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }


}
