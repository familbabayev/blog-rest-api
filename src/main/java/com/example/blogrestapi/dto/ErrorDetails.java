package com.example.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ErrorDetails {
    private HttpStatus status;
    private String message;
}
