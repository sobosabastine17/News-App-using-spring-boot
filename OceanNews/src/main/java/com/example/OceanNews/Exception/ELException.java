package com.example.OceanNews.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ELException extends RuntimeException{
    public ELException(String message) {
        super(message);
    }
}