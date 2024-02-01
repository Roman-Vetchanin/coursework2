package com.exam.examToJava.controller;

import com.exam.examToJava.model.Question;
import com.exam.examToJava.service.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/exam/get")
@RestController
public class ExamController {

    private final ExaminerService examinerService;


    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getRandomQuestion(@PathVariable("amount") int amount) {
        return examinerService.getQuestion(amount);
    }
}
