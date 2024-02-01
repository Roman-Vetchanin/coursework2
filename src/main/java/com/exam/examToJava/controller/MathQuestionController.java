package com.exam.examToJava.controller;

import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("java/math")
@RestController
public class MathQuestionController {
    @Autowired
    @Qualifier("mathQuestionServiceImpl")
    private final QuestionService mathQuestionService;


    public MathQuestionController(QuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return mathQuestionService.add(new Question(question, answer));
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question) {
        return mathQuestionService.remove(new Question(question, null));
    }

    @GetMapping("/getAll")
    public Collection<Question> getAllQuestion() {
        return mathQuestionService.getAll();
    }
}
