package dev.peertosir.questheap.exception.answer;

import dev.peertosir.questheap.exception.base.client.NotFoundException;

public class AnswerNotFoundException extends NotFoundException {
    public AnswerNotFoundException(String message) {
        super(message);
    }

    public AnswerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
