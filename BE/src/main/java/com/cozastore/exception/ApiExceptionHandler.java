package com.cozastore.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> apiExceptionHandler(BadRequestException e) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		ApiException apiException = new ApiException(e.getMessage(), statusCode);
		return new ResponseEntity<>(apiException, statusCode);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> apiExceptionHandler(NotFoundException e) {
		HttpStatus statusCode = HttpStatus.NOT_FOUND;

		ApiException apiException = new ApiException(e.getMessage(), statusCode);
		return new ResponseEntity<>(apiException, statusCode);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> apiExceptionHandler(HttpMessageNotReadableException e) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		ApiException apiException = new ApiException(e.getMessage(), statusCode);
		return new ResponseEntity<>(apiException, statusCode);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> apiExceptionHandler(MethodArgumentNotValidException e) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		ApiException apiException = new ApiException(e.getMessage(), statusCode);
		return new ResponseEntity<>(apiException, statusCode);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> argumentNotValidMultipartExceptionHandler(BindException ex) {
		String message = "";
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		for (FieldError error : ex.getFieldErrors()) {
			message += error.getDefaultMessage() + ". \n";
		}
		ApiException apiException = new ApiException(message, statusCode);
		return new ResponseEntity<>(apiException, statusCode);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> apiExceptionHandler(ConstraintViolationException e) {
		HttpStatus statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
		System.out.println();
		List<String> errorMessages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toCollection(ArrayList::new));
		ApiException apiException = new ApiException(errorMessages.toString(), statusCode);
		return new ResponseEntity<>(apiException, statusCode);
	}

	@ExceptionHandler(InputFieldException.class)
	public ResponseEntity<Map<String, String>> handleInputFieldException(InputFieldException exception) {
		InputFieldException inputFieldException = new InputFieldException(exception.getBindingResult());
		return ResponseEntity.badRequest().body(inputFieldException.getErrorsMap());
	}
}