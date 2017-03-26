package com.pw.Logic;

import java.util.List;

/**
 * Created by Karolina on 26.03.2017.
 */
public interface QuestionService {
    public List<Question> get10RandomQuestions(Category category);
}
