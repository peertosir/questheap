package dev.peertosir.questheap.controller;

import dev.peertosir.questheap.domain.Question;
import dev.peertosir.questheap.dto.base.CreatedEntityIdTo;
import dev.peertosir.questheap.dto.question.QuestionDto;
import dev.peertosir.questheap.mapper.QuestionMapper;
import dev.peertosir.questheap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/question")
public class QuestionController {
    private final QuestionService questionService;

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestion(@PathVariable("id") long id) {
        return questionMapper.map(questionService.getQuestion(id));
    }

    @PostMapping
    public CreatedEntityIdTo createQuestion(@Valid @RequestBody QuestionDto questionDto) {
        long id = questionService.createQuestion(questionMapper.map(questionDto));
        return new CreatedEntityIdTo(id);
    }

    @PutMapping("/{id}")
    public QuestionDto updateQuestion(
            @Valid @RequestBody QuestionDto questionDto,
            @PathVariable("id") long id
    ) {
        Question question = questionMapper.map(questionDto);
        return questionMapper.map(questionService.updateQuestion(question, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable("id") long id) {
        questionService.deleteQuestion(id);
    }
}
