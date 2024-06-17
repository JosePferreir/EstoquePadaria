package com.example.padaria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MateriaPrimaNotFoundException.class)
    public ResponseEntity<?> handleMateriaPrimaNotFoundException(MateriaPrimaNotFoundException ex, WebRequest request) {
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MateriaPrimaExpiredException.class)
    public ResponseEntity<?> handleMateriaPrimaExpiredException(MateriaPrimaExpiredException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MateriaPrimaInsufficientException.class)
    public ResponseEntity<?> handleMateriaPrimaInsufficientException(MateriaPrimaInsufficientException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
