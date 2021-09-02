package dev.peertosir.questheap.service;

import dev.peertosir.questheap.domain.Question;

public interface QuestionService {
    Question getQuestion(long id);
    long createQuestion(Question questionDto);
    Question updateQuestion(Question questionDto, long id);
    void deleteQuestion(long id);
}
