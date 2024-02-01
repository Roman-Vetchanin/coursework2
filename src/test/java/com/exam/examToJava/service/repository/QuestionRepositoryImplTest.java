package com.exam.examToJava.service.repository;

import com.exam.examToJava.exceptions.ParameterIsEmptyException;
import com.exam.examToJava.exceptions.QuestionIsNotFoundException;
import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryImplTest {

    private final QuestionRepository questionRepository = new QuestionRepositoryImpl();

    @BeforeEach
    public void beforeEach() {
        questionRepository.add(new Question("Какой то", "Вопрос 1"));
        questionRepository.add(new Question("Какой то", "Вопрос 2"));
        questionRepository.add(new Question("Какой то", "Вопрос 3"));
        questionRepository.add(new Question("Какой то", "Вопрос 4"));
        questionRepository.add(new Question("Какой то", "Вопрос 5"));
    }

    @AfterEach
    public void afterEach() {
        Collection<Question> questions = new ArrayList<>(questionRepository.getAll());
        for (Question question : questions) {
            questionRepository.remove(question);
        }
    }

    @Test
    void addPositiveTest() {
        Question expected = new Question("Какой то", "Вопрос");
        questionRepository.add(expected);
        assertThat(questionRepository.getAll()).contains(expected);
    }
    @Test
    void addNegativeTest() {
        Question expected = new Question(null, null);
        assertThatExceptionOfType(ParameterIsEmptyException.class).isThrownBy(()->questionRepository.add(expected));
        Question expected2 = new Question("Какой то", null);
        assertThatExceptionOfType(ParameterIsEmptyException.class).isThrownBy(()->questionRepository.add(expected2));
        Question expected3 = new Question(null, "Вопрос");
        assertThatExceptionOfType(ParameterIsEmptyException.class).isThrownBy(()->questionRepository.add(expected3));
    }

    @Test
    void removePositiveTest() {
        Question expected = new Question("Какой то", "Вопрос");
        questionRepository.add(expected);
        assertThat(questionRepository.getAll()).contains(expected);
        questionRepository.remove(expected);
        assertThat(questionRepository.getAll()).doesNotContain(expected);
    }
    @Test
    void removeNegativeTest() {
        Question expected = new Question("Какой то", "Вопрос");
        assertThat(questionRepository.getAll()).doesNotContain(expected);
        assertThatExceptionOfType(QuestionIsNotFoundException.class).isThrownBy(() -> questionRepository.remove(expected));
    }



    @Test
    void findPositiveTest() {
        Question expected = new Question("Какой то", "Вопрос");
        questionRepository.add(expected);
        assertThat(questionRepository.getAll()).contains(expected);
        assertThat(questionRepository.find(expected)).isEqualTo(expected);
    }
    @Test
    void findNegativeTest() {
        Question expected = new Question("Какой то", "Вопрос");
        assertThatExceptionOfType(QuestionIsNotFoundException.class).isThrownBy(() -> questionRepository.find(expected));
    }

    @Test
    void getAll() {
        assertThat(questionRepository.getAll()).hasSize(5)
                .containsExactlyInAnyOrder(
                        new Question("Какой то", "Вопрос 1"),
                        new Question("Какой то", "Вопрос 2"),
                        new Question("Какой то", "Вопрос 3"),
                        new Question("Какой то", "Вопрос 4"),
                        new Question("Какой то", "Вопрос 5"));
    }
}