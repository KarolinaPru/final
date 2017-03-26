package com.pw.Logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karolina on 26.03.2017.
 */
public class QuestionServiceImplHardcoded implements QuestionService {
    private ArrayList<QuestionInterface> questions;

    @Override
    public List<QuestionInterface> getQuestions(Category category) {
        // MAGIC HERE!

        return questions;
    }
}


