package dev.peertosir.questheap.exception.question;

import dev.peertosir.questheap.exception.base.client.NotFoundException;

public class QuestionNotFoundException extends NotFoundException {

    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
