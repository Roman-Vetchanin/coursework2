package com.exam.examToJava.service;

import com.exam.examToJava.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
