package dev.peertosir.questheap.service;

import dev.peertosir.questheap.domain.Quiz;
import dev.peertosir.questheap.dto.quiz.QuizTo;
import dev.peertosir.questheap.exception.quiz.QuizNotFoundException;
import dev.peertosir.questheap.repository.QuizRepository;
import dev.peertosir.questheap.tranformer.QuizTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizTransformer quizTransformer;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizTransformer quizTransformer) {
        this.quizRepository = quizRepository;
        this.quizTransformer = quizTransformer;
    }

    public Quiz getQuiz(long id) {
        return this.quizRepository.findById(id).orElseThrow(
                () -> new QuizNotFoundException("Quiz with id=" + id + " not found")
        );
    }

    public List<Quiz> getQuizes() {
        return quizRepository.findAll();
    }

    public long createQuiz(QuizTo quizTo) {
        quizTo.setId(0);
        Quiz quiz = quizTransformer.toQuiz(quizTo);
        quiz = quizRepository.saveAndFlush(quiz);
        return quiz.getId();
    }

    public Quiz updateQuiz(QuizTo quizTo, long id) {
        quizTo.setId(id);
        Quiz quiz = quizTransformer.toQuiz(quizTo);
        quiz = quizRepository.saveAndFlush(quiz);
        return quiz;
    }

    public void deleteQuiz(long id) {
        Quiz quiz = getQuiz(id);
        quizRepository.delete(quiz);
    }
}
