package com.exam.examToJava.service;

import com.exam.examToJava.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Question find(Question question);
    Collection<Question> getAll();
}
