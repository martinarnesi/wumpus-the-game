package com.arnesi.wumpus.exception;

public class InvalidCavePositionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidCavePositionException(String message) {
		super(message);
	}

	public InvalidCavePositionException(String message, Throwable cause) {
		super(message, cause);
	}
}
