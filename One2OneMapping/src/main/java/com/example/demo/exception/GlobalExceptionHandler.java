package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RestControllerAdvice

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Bad Request you are sending", code = HttpStatus.BAD_GATEWAY)
public class GlobalExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
		return new ResponseEntity<>("RuntimeException", HttpStatus.OK);
	}
}
