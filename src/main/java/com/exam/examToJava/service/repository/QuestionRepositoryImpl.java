package com.exam.examToJava.service.repository;

import com.exam.examToJava.exceptions.ParameterIsEmptyException;
import com.exam.examToJava.exceptions.QuestionIsNotFoundException;
import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(Question question) {
        if (question.getAnswer()==null||question.getQuestion()==null) {
            throw new ParameterIsEmptyException("Параметры не должны быть пустыми");
        } else {
            questions.add(question);
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionIsNotFoundException("Удаляемый вопрос не найден");
        }
        return question;
    }

    @Override
    public Question find(Question question) {
        for (Question q : questions) {
            if (q.getQuestion().equals(question.getQuestion())&&q.getAnswer().equals(question.getAnswer())) {
                return question;
            }
        }
        throw new QuestionIsNotFoundException("Такого вопроса нет");
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }
}
