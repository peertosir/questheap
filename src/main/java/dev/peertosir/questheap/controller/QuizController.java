package dev.peertosir.questheap.controller;


import dev.peertosir.questheap.dto.base.CreatedEntityIdTo;
import dev.peertosir.questheap.dto.quiz.QuizTo;
import dev.peertosir.questheap.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/quiz")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuizTo getQuiz(@PathVariable("id") long id) {
        return quizService.getQuiz(id);
    }

    @GetMapping
    public List<QuizTo> getQuizes() {
        return quizService.getQuizes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedEntityIdTo createQuiz(@Valid @RequestBody QuizTo quizTo) {
        return new CreatedEntityIdTo(quizService.createQuiz(quizTo));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuizTo updateQuiz(@Valid @RequestBody QuizTo quizTo, @PathVariable("id") long id) {
        return quizService.updateQuiz(quizTo, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable("id") long id) {
        quizService.deleteQuiz(id);
    }
}
