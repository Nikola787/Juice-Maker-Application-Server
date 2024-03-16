package com.fpis.fontazija.kokteli.exceptions.handler;

import com.fpis.fontazija.kokteli.exceptions.ObjectNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleException(ObjectNotValidException exp) {
        return new ResponseEntity<>(exp.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

}
