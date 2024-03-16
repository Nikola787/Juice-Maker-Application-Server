package com.fpis.fontazija.kokteli.exceptions;

import lombok.Getter;

import java.util.Objects;
import java.util.Set;

public class ObjectNotValidException extends RuntimeException {

    private final Set<String> errorMessages;

    public ObjectNotValidException(Set<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ObjectNotValidException(String message, Set<String> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

    public ObjectNotValidException(String message, Throwable cause, Set<String> errorMessages) {
        super(message, cause);
        this.errorMessages = errorMessages;
    }

    public ObjectNotValidException(Throwable cause, Set<String> errorMessages) {
        super(cause);
        this.errorMessages = errorMessages;
    }

    public ObjectNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Set<String> errorMessages) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMessages = errorMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectNotValidException that = (ObjectNotValidException) o;
        return Objects.equals(errorMessages, that.errorMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessages);
    }

    @Override
    public String toString() {
        return "ObjectNotValidException{" +
                "errorMessages=" + errorMessages +
                '}';
    }

    public Set<String> getErrorMessages() {
        return errorMessages;
    }

}
