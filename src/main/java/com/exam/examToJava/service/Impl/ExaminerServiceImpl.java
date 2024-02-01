package com.exam.examToJava.service.Impl;

import com.exam.examToJava.exceptions.OutOfBeyondTheNumberOfQuestionsException;
import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.ExaminerService;
import com.exam.examToJava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    @Qualifier("javaQuestionService")
    private final QuestionService questionService;
    @Qualifier("mathQuestionService")
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(QuestionService questionService, QuestionService mathQuestionService) {
        this.questionService = questionService;
        this.mathQuestionService = mathQuestionService;
    }


    @Override
    public Collection<Question> getQuestion(int amount) {
        if (questionService.getAll().size() + mathQuestionService.getAll().size() < amount) {
            throw new OutOfBeyondTheNumberOfQuestionsException();
        }
        Random random = new Random();
        Set<Question> list = new HashSet<>();
        while (list.size() < amount) {
            if (random.nextBoolean()) {
                list.add(questionService.getRandomQuestion());
            } else {
                list.add(mathQuestionService.getRandomQuestion());
            }
        }
        return list;
    }
}
