package com.md.demo.exception;

public class NoSuchRatingExistException extends RuntimeException {

	public NoSuchRatingExistException(String message) {
		super(message);
	}
}
