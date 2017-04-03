package com.pw.Logic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by Karolina on 25.03.2017.
 */
@Getter
@EqualsAndHashCode
public class AnswerImpl implements Answer {
    private String answer;
    private boolean isCorrect;
    private static long nextAvailableId;
    @Getter
    private long id = 1;

    public AnswerImpl(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
        id = nextAvailableId;
        nextAvailableId++;
    }

}
