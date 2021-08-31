package dev.peertosir.questheap.exception.quiz;

import dev.peertosir.questheap.exception.base.client.NotFoundException;

public class QuizNotFoundException extends NotFoundException {
    public QuizNotFoundException(String message) {
        super(message);
    }

    public QuizNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
