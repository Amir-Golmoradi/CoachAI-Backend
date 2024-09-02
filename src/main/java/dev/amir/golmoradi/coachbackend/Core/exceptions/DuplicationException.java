package dev.amir.golmoradi.coachbackend.Core.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class DuplicationException extends RuntimeException {
    public DuplicationException(String message) {
        super(message);
    }
}
