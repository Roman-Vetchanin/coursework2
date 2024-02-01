package com.exam.examToJava.service.Impl;

import com.exam.examToJava.exceptions.QuestionAreEmptyException;
import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.QuestionRepository;
import com.exam.examToJava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.*;

public class MathQuestionServiceImpl implements QuestionService {
    @Autowired
    @Qualifier("mathQuestionRepository")
    private final QuestionRepository mathQuestionRepository;

    public MathQuestionServiceImpl(QuestionRepository questionRepository) {
        this.mathQuestionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return mathQuestionRepository.add(question);
    }


    @Override
    public Question find(Question question) {
        return mathQuestionRepository.find(question);
    }

    @Override
    public Question remove(Question question) {
        return mathQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (mathQuestionRepository.getAll().isEmpty()) {
            throw new QuestionAreEmptyException();
        }
        Random random = new Random();
        List<Question> list = new ArrayList<>(mathQuestionRepository.getAll());
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
