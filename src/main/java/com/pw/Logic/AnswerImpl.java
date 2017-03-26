package com.pw.Logic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Karolina on 25.03.2017.
 */
@Getter
@EqualsAndHashCode
public class AnswerImpl implements Answer {
    private String answer;
    private boolean isCorrect;

    public AnswerImpl(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }


}
