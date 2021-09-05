package dev.peertosir.questheap.service;

import dev.peertosir.questheap.domain.Answer;

public interface AnswerService {
    Answer getAnswer(long id);
    long createAnswer(Answer answer);
    Answer updateAnswer(Answer answer, long id);
    void deleteAnswer(long id);
}
