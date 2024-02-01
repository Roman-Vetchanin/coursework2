package com.exam.examToJava.service.repository;

import com.exam.examToJava.exceptions.ParameterIsEmptyException;
import com.exam.examToJava.exceptions.QuestionIsNotFoundException;
import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.QuestionRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> questionSet = new HashSet<>();

    @Override
    public Question add(Question question) {
        if (question.getQuestion() == null || question.getAnswer() == null) {
            throw new ParameterIsEmptyException("Параметры не должны быть пустыми");
        } else {
            questionSet.add(question);
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionSet.remove(question)) {
            throw new QuestionIsNotFoundException("Удаляемый вопрос не найден");
        }
        return question;
    }

    @Override
    public Question find(Question question) {
        for (Question q : questionSet) {
            if (q.getQuestion().equals(question.getQuestion())&&q.getAnswer().equals(question.getAnswer())) {
                return question;
            }
        }
        throw new QuestionIsNotFoundException("Такого вопроса нет");
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questionSet);
    }
}
