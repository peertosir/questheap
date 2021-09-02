package dev.peertosir.questheap.service.impl;

import dev.peertosir.questheap.domain.Question;
import dev.peertosir.questheap.exception.question.QuestionNotFoundException;
import dev.peertosir.questheap.repository.QuestionRepository;
import dev.peertosir.questheap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question getQuestion(long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question with id: " + id + " not found")
        );
    }

    @Override
    public long createQuestion(Question question) {
        question.setId(0);
        question = questionRepository.saveAndFlush(question);
        return question.getId();
    }

    @Override
    public Question updateQuestion(Question question, long id) {
        question.setId(id);
        question = questionRepository.saveAndFlush(question);
        return question;
    }

    @Override
    public void deleteQuestion(long id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new QuestionNotFoundException("Question with id: " + id + " not found")
        );
        question.getQuiz().deleteQuestion(question);
        questionRepository.deleteById(id);
    }
}
