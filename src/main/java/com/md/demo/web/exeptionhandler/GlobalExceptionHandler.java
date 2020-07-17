package com.md.demo.web.exeptionhandler;

import com.md.demo.exception.NoImageToUploadException;
import com.md.demo.exception.NoSuchCommentExistException;
import com.md.demo.exception.NoSuchItemExistException;
import com.md.demo.exception.NoSuchRatingExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(NoSuchItemExistException.class)
	public ResponseEntity<?> handleNoSuchItemExistException(NoSuchItemExistException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Sorry, No such item!");
		LOGGER.error("An error was occurred: " + ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchRatingExistException.class)
	public ResponseEntity<?> handleNoSuchRatingExistException(NoSuchRatingExistException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Sorry, No such rating!");
		LOGGER.error("An error was occurred: " + ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchCommentExistException.class)
	public ResponseEntity<?> handleNoSuchCommentExistException(NoSuchCommentExistException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Sorry, No such comment!");
		LOGGER.error("An error was occurred: " + ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoImageToUploadException.class)
	public ResponseEntity<?> handleNoNoImageToUploadException(NoImageToUploadException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Hey, Select an image to upload!");
		LOGGER.error("An error was occurred: " + ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
