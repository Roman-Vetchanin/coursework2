package com.exam.examToJava.service.repository;

import com.exam.examToJava.exceptions.ParameterIsEmptyException;
import com.exam.examToJava.exceptions.QuestionIsNotFoundException;
import com.exam.examToJava.model.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class MathQuestionRepositoryTest {

    private final MathQuestionRepository mathQuestionRepository = new MathQuestionRepository();


    @AfterEach
    public void afterEach() {
        Collection<Question> questions = new ArrayList<>(mathQuestionRepository.getAll());
        for (Question question : questions) {
            mathQuestionRepository.remove(question);
        }
    }

    @BeforeEach
    public void beforeEach() {
        mathQuestionRepository.add(new Question("Какой то", "Вопрос 1"));
        mathQuestionRepository.add(new Question("Какой то", "Вопрос 2"));
        mathQuestionRepository.add(new Question("Какой то", "Вопрос 3"));
        mathQuestionRepository.add(new Question("Какой то", "Вопрос 4"));
        mathQuestionRepository.add(new Question("Какой то", "Вопрос 5"));
    }



    @Test
    void addPositiveTest() {
        Question expected = new Question("Какой то", "Вопрос");
        mathQuestionRepository.add(expected);
        assertThat(mathQuestionRepository.getAll()).contains(expected);
    }
    @Test
    void addNegativeTest() {
        Question expected = new Question(null, null);
        assertThatExceptionOfType(ParameterIsEmptyException.class).isThrownBy(()->mathQuestionRepository.add(expected));
        Question expected2 = new Question("Какой то", null);
        assertThatExceptionOfType(ParameterIsEmptyException.class).isThrownBy(()->mathQuestionRepository.add(expected2));
        Question expected3 = new Question(null, "Вопрос");
        assertThatExceptionOfType(ParameterIsEmptyException.class).isThrownBy(()->mathQuestionRepository.add(expected3));
    }

    @Test
    void removePositiveTest() {
        Question expected = new Question("Какой то", "Вопрос");
        mathQuestionRepository.add(expected);
        assertThat(mathQuestionRepository.getAll()).contains(expected);
        mathQuestionRepository.remove(expected);
        assertThat(mathQuestionRepository.getAll()).doesNotContain(expected);
    }
    @Test
    void removeNegativeTest() {
        Question expected = new Question("Какой то", "Вопрос");
        assertThat(mathQuestionRepository.getAll()).doesNotContain(expected);
        assertThatExceptionOfType(QuestionIsNotFoundException.class).isThrownBy(() -> mathQuestionRepository.remove(expected));
    }



    @Test
    void findPositiveTest() {
        Question expected = new Question("Какой то", "Вопрос");
        mathQuestionRepository.add(expected);
        assertThat(mathQuestionRepository.getAll()).contains(expected);
        assertThat(mathQuestionRepository.find(expected)).isEqualTo(expected);
    }
    @Test
    void findNegativeTest() {
        Question expected = new Question("Какой то", "Вопрос");
        assertThatExceptionOfType(QuestionIsNotFoundException.class).isThrownBy(() -> mathQuestionRepository.find(expected));
    }

    @Test
    void getAll() {
        assertThat(mathQuestionRepository.getAll()).hasSize(5)
                .containsExactlyInAnyOrder(
                        new Question("Какой то", "Вопрос 1"),
                        new Question("Какой то", "Вопрос 2"),
                        new Question("Какой то", "Вопрос 3"),
                        new Question("Какой то", "Вопрос 4"),
                        new Question("Какой то", "Вопрос 5"));
    }
}