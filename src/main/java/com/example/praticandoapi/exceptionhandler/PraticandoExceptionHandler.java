package com.example.praticandoapi.exceptionhandler;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PraticandoExceptionHandler extends ResponseEntityExceptionHandler  {
	
	
	protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, "Errado", headers, HttpStatus.BAD_REQUEST, request);
	}

}
