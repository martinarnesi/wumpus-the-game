package com.arnesi.wumpus.exception;

public class InvalidEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidEntityException(String message) {
		super(message);
	}

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);
	}
}