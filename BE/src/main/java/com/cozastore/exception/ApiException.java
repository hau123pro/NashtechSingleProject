package com.cozastore.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private String message;
    private HttpStatus status;
    public ApiException(String message) {
    	this.message=message;
    }
}