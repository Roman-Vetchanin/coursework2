package com.exam.examToJava.service.Impl;

import com.exam.examToJava.exceptions.ParameterIsEmptyException;
import com.exam.examToJava.exceptions.QuestionAreEmptyException;
import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.QuestionRepository;
import com.exam.examToJava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    @Autowired
    @Qualifier("questionRepositoryImpl")
    private final QuestionRepository questions;

    public JavaQuestionService(QuestionRepository questions) {
        this.questions = questions;
    }


    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return questions.add(question);
    }


    @Override
    public Question find(Question question) {
        return questions.find(question);
    }

    @Override
    public Question remove(Question question) {
        return questions.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questions.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.getAll().isEmpty()) {
            throw new QuestionAreEmptyException();
        }
        Random random = new Random();
        List<Question> list = new ArrayList<>(questions.getAll());
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
