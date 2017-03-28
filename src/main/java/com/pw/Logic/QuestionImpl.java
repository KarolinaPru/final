package com.pw.Logic;

import groovy.transform.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Karolina on 24.03.2017.
 */

@Getter
@Builder
@EqualsAndHashCode
public class QuestionImpl implements Question {
    private Category category;
    private String question;
    private List<Answer> answers;
    private UUID id;


    public QuestionImpl(Category category, String question, List<Answer> answers) {
        this.category = category;
        this.question = question;
        this.answers = answers;
        id = UUID.randomUUID();
    }


}

