package dev.peertosir.questheap.service.impl;

import dev.peertosir.questheap.domain.Answer;
import dev.peertosir.questheap.exception.answer.AnswerNotFoundException;
import dev.peertosir.questheap.repository.AnswerRepository;
import dev.peertosir.questheap.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer getAnswer(long id) {
        return answerRepository.findById(id).orElseThrow(
                () -> new AnswerNotFoundException("Answer with id: " + id + " not found")
        );
    }

    @Override
    public long createAnswer(Answer answer) {
        answer.setId(0);
        answer = answerRepository.saveAndFlush(answer);
        return answer.getId();
    }

    @Override
    public Answer updateAnswer(Answer answer, long id) {
        answer.setId(id);
        return answerRepository.saveAndFlush(answer);
    }

    @Override
    public void deleteAnswer(long id) {
        getAnswer(id);
        answerRepository.deleteById(id);
    }
}
