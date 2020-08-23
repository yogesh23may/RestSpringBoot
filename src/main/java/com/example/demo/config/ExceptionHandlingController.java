package com.example.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.exception.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandlingController {
	
	//Global exception handling for Exception for all the controllers
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exceptionResponse.setErrorMessage(exception.getMessage());
		exceptionResponse.setExceptionHandlingLevel("global-level");
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
