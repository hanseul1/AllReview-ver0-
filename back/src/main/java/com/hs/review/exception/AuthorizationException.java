package com.hs.review.exception;

public class AuthorizationException extends RuntimeException {

	public AuthorizationException() {
		super("Authorization Exception");
	}

	public AuthorizationException(String message) {
		super(message);
	}
	
}
