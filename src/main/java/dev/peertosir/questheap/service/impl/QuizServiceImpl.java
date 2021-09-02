package dev.peertosir.questheap.service.impl;

import dev.peertosir.questheap.domain.Quiz;
import dev.peertosir.questheap.dto.quiz.QuizTo;
import dev.peertosir.questheap.exception.quiz.QuizNotFoundException;
import dev.peertosir.questheap.mapper.QuizMapper;
import dev.peertosir.questheap.repository.QuizRepository;
import dev.peertosir.questheap.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public QuizTo getQuiz(long id) {
        Quiz quiz = this.quizRepository.findById(id).orElseThrow(
                () -> new QuizNotFoundException("Quiz with id=" + id + " not found")
        );
        return quizMapper.map(quiz);
    }

    @Override
    public List<QuizTo> getQuizes() {
        return quizMapper.map(quizRepository.findAll());
    }

    @Override
    public long createQuiz(QuizTo quizTo) {
        quizTo.setId(0);
        Quiz quiz = quizMapper.map(quizTo);
        quiz = quizRepository.saveAndFlush(quiz);
        return quiz.getId();
    }

    @Override
    public QuizTo updateQuiz(QuizTo quizTo, long id) {
        quizTo.setId(id);
        Quiz quiz = quizMapper.map(quizTo);
        quizRepository.saveAndFlush(quiz);
        return quizMapper.map(quiz);
    }

    @Override
    public void deleteQuiz(long id) {
        getQuiz(id);
        quizRepository.deleteById(id);
    }
}
