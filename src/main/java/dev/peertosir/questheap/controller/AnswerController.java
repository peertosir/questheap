package dev.peertosir.questheap.controller;

import dev.peertosir.questheap.domain.Answer;
import dev.peertosir.questheap.dto.answer.AnswerDto;
import dev.peertosir.questheap.dto.base.CreatedEntityIdTo;
import dev.peertosir.questheap.mapper.AnswerMapper;
import dev.peertosir.questheap.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @Autowired
    public AnswerController(AnswerService answerService, AnswerMapper answerMapper) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @GetMapping("/{id}")
    public AnswerDto getAnswer(@PathVariable("id") long id) {
        return answerMapper.map(answerService.getAnswer(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedEntityIdTo createAnswer(@Valid @RequestBody AnswerDto answerDto) {
        long id = answerService.createAnswer(answerMapper.map(answerDto));
        return new CreatedEntityIdTo(id);
    }

    @PutMapping("/{id}")
    public AnswerDto updateAnswer(@Valid @RequestBody AnswerDto answerDto, @PathVariable("id") long id) {
        Answer answer = answerService.updateAnswer(answerMapper.map(answerDto), id);
        return answerMapper.map(answer);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnswer(long id) {
        answerService.deleteAnswer(id);
    }


}
