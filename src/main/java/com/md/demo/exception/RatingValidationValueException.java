package com.md.demo.exception;

public class RatingValidationValueException extends RuntimeException {

	public RatingValidationValueException(String name) {
		super(name);
	}
}
