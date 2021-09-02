package dev.peertosir.questheap.exception.base.client;

import dev.peertosir.questheap.exception.base.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ApiException {
    public BadRequestException(String message, Throwable cause) {
        super(message, cause, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message, null, HttpStatus.BAD_REQUEST);
    }
}
