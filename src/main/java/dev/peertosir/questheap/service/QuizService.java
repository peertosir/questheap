package dev.peertosir.questheap.service;

import dev.peertosir.questheap.dto.quiz.QuizTo;

import java.util.List;

public interface QuizService {
    QuizTo getQuiz(long id);

    List<QuizTo> getQuizes();

    long createQuiz(QuizTo quizTo);

    QuizTo updateQuiz(QuizTo quizTo, long id);

    void deleteQuiz(long id);
}
