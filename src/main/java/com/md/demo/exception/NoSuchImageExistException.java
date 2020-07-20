package com.md.demo.exception;

public class NoSuchImageExistException extends RuntimeException {

	public NoSuchImageExistException(String message) {
		super(message);
	}
}
