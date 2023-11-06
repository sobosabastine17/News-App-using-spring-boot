package com.example.OceanNews.Exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // Create and return a custom error response
        return ResponseEntity.status(500).body(new ErrorResponse(){
            @Override
            public HttpStatusCode getStatusCode() {
                return null;
            }

            @Override
            public ProblemDetail getBody() {
                return null;
            }
        });
    }
}
