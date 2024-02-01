package com.exam.examToJava.service.Impl;

import com.exam.examToJava.exceptions.OutOfBeyondTheNumberOfQuestionsException;
import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private final QuestionService questionService = mock(JavaQuestionService.class);
    private final QuestionService mathQuestionService = mock(MathQuestionServiceImpl.class);


    private final ExaminerServiceImpl examinerService = new ExaminerServiceImpl(questionService, mathQuestionService);

    private final List<Question> questions = List.of(
            new Question("Какой то", "Вопрос 1"),
            new Question("Какой то", "Вопрос 2"),
            new Question("Какой то", "Вопрос 3"),
            new Question("Какой то", "Вопрос 4"),
            new Question("Какой то", "Вопрос 5"));

    private final List<Question> mathQuestions = List.of(
            new Question("Какой то 1", "Вопрос 1"),
            new Question("Какой то 2", "Вопрос 2"),
            new Question("Какой то 3", "Вопрос 3"),
            new Question("Какой то 4", "Вопрос 4"),
            new Question("Какой то 5", "Вопрос 5"));


    @BeforeEach
    public void beforeEach() {
        when(questionService.getAll()).thenReturn(questions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

    }

    @Test
    void getQuestionNegativeTest() {
        assertThatExceptionOfType(OutOfBeyondTheNumberOfQuestionsException.class).isThrownBy(() -> examinerService.getQuestion(questions.size()+mathQuestions.size() + 1));
    }

    @Test
    void getQuestionPositiveTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Какой то", "Вопрос 3"),
                new Question("Какой то", "Вопрос 3"),
                new Question("Какой то", "Вопрос 5"),
                new Question("Какой то", "Вопрос 5"),
                new Question("Какой то", "Вопрос 4"),
                new Question("Какой то", "Вопрос 4"),
                new Question("Какой то", "Вопрос 2"),
                new Question("Какой то", "Вопрос 1"),
                new Question("Какой то", "Вопрос 1")
        );
        when(mathQuestionService.getRandomQuestion()).thenReturn(
                new Question("Какой то 3", "Вопрос 3"),
                new Question("Какой то 3", "Вопрос 3"),
                new Question("Какой то 5", "Вопрос 5"),
                new Question("Какой то 5", "Вопрос 5"),
                new Question("Какой то 4", "Вопрос 4"),
                new Question("Какой то 4", "Вопрос 4"),
                new Question("Какой то 2", "Вопрос 2"),
                new Question("Какой то 1", "Вопрос 1"),
                new Question("Какой то 1", "Вопрос 1")
        );

        Collection<Question> getRandom = examinerService.getQuestion(7);
        assertThat(getRandom).hasSize(7);
        for (Question q : getRandom) {
            assertThat(q).isIn(new Question("Какой то", "Вопрос 1"),
                    new Question("Какой то", "Вопрос 2"),
                    new Question("Какой то", "Вопрос 3"),
                    new Question("Какой то", "Вопрос 4"),
                    new Question("Какой то", "Вопрос 5"),
                    new Question("Какой то 1", "Вопрос 1"),
                    new Question("Какой то 2", "Вопрос 2"),
                    new Question("Какой то 3", "Вопрос 3"),
                    new Question("Какой то 4", "Вопрос 4"),
                    new Question("Какой то 5", "Вопрос 5"));
        }
    }
}
