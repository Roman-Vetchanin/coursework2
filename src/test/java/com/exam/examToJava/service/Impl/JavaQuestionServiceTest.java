package com.exam.examToJava.service.Impl;

import com.exam.examToJava.exceptions.QuestionAreEmptyException;
import com.exam.examToJava.exceptions.QuestionIsNotFoundException;
import com.exam.examToJava.model.Question;

import com.exam.examToJava.service.repository.QuestionRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private QuestionRepositoryImpl questionRepository;
    @InjectMocks
    private JavaQuestionService questionService;


    private final List<Question> questions = List.of(
            new Question("Какой то", "Вопрос 1"),
            new Question("Какой то", "Вопрос 2"),
            new Question("Какой то", "Вопрос 3"),
            new Question("Какой то", "Вопрос 4"),
            new Question("Какой то", "Вопрос 5"));


    @Test
    void addQuestionTest() {
        Question expected = new Question("Какой то", "Вопрос");
        when(questionRepository.add(expected)).thenReturn(expected);
        questionService.add(expected);

    }

    @Test
    void findTest() {
        Question expected = new Question("Какой то", "Вопрос 1");
        when(questionRepository.find(eq(expected))).thenThrow(QuestionIsNotFoundException.class);
        assertThatExceptionOfType(QuestionIsNotFoundException.class)
                .isThrownBy(() -> questionService.find(expected));
    }

    @Test
    void removeTest() {

        when(questionRepository.remove(eq(new Question("Какой то", "Вопрос")))).thenThrow(QuestionIsNotFoundException.class);
        assertThatExceptionOfType(QuestionIsNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Какой то", "Вопрос")));

    }

    @Test
    void getAllTest() {
        when(questionRepository.getAll()).thenReturn(questions);
        assertThat(questionService.getAll()).hasSize(5)
                .containsExactlyInAnyOrder(
                        new Question("Какой то", "Вопрос 1"),
                        new Question("Какой то", "Вопрос 2"),
                        new Question("Какой то", "Вопрос 3"),
                        new Question("Какой то", "Вопрос 4"),
                        new Question("Какой то", "Вопрос 5"));
    }

    @Test
    void getRandomQuestion() {
        when(questionRepository.getAll()).thenReturn(questions);
        Question actual = questionService.getRandomQuestion();
        assertThat(actual).isIn(questionService.getAll());
    }

    @Test
    void getRandomQuestionNegativeTest() {
        when(questionRepository.getAll()).thenReturn(Collections.emptyList());
        assertThatExceptionOfType(QuestionAreEmptyException.class).isThrownBy(questionService::getRandomQuestion);
    }
}
