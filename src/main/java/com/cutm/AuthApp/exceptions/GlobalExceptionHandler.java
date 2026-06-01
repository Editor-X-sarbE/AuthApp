package com.cutm.AuthApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cutm.AuthApp.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        // resource not found handler method
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
                        ResourceNotFoundException exception) {

                ErrorResponse errorResponse = new ErrorResponse(
                                exception.getMessage(),
                                HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(errorResponse);
        }

        // email not found handler method
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
                        IllegalArgumentException exception) {

                ErrorResponse errorResponse = new ErrorResponse(
                                exception.getMessage(),
                                HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(errorResponse);
        }
}