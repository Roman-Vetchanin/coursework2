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

@RequestMapping("/exam/java")
@RestController
public class JavaController {
    private final QuestionService questionService;


    public JavaController(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(new Question(question, answer));
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.remove(new Question(question,answer));
    }

    @GetMapping
    public Collection<Question> getAllQuestion() {
        return questionService.getAll();
    }
}
