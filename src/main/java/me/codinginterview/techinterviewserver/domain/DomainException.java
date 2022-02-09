package me.codinginterview.techinterviewserver.domain;

import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Getter
public class DomainException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    private DomainException(@NonNull HttpStatus status,
                            @NonNull String message) {
        super(message);
        if (StringUtils.isBlank(message)) {
            throw new IllegalArgumentException("exception message is empty");
        }
        this.status = status;
        this.message = message;
    }

    public static class BadRequest extends DomainException {
        public BadRequest(String message) {
            super(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static class Conflict extends DomainException {
        public Conflict(String message) {
            super(HttpStatus.CONFLICT, message);
        }
    }
}
