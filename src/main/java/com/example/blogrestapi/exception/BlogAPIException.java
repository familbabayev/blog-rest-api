package com.example.blogrestapi.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BlogAPIException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
}

