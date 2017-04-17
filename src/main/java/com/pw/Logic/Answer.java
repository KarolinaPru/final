package com.pw.Logic;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Karolina on 25.03.2017.
 */
@Getter
@EqualsAndHashCode
public class Answer {
    private final String answer;
    private final boolean isCorrect;

    public Answer(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
}
