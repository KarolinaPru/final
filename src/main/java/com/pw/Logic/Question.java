package com.pw.Logic;

import java.util.List;
import java.util.UUID;

/**
 * Created by Karolina on 25.03.2017.
 */
public interface Question {

    public List<Answer> getAnswers();
    public List<Long> getIdsOfCorrectAnswers(List<Answer> answers);

}
