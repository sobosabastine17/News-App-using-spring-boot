package com.example.OceanNews.Exception;

public class BadRequest extends RuntimeException{
    public BadRequest(String message) {
        super(message);
    }
}