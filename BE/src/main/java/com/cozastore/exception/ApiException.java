package com.cozastore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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