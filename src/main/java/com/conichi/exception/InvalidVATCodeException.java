package com.conichi.exception;

import org.springframework.http.HttpStatus;

public class InvalidVATCodeException extends Exception {

	private HttpStatus value;
	private String message;

	public InvalidVATCodeException(HttpStatus value, String message) {
		this.value = value;
		this.message = message;
	}

	public HttpStatus getValue() {
		return value;
	}

	public String getMessage() {
		return this.message;
	}

}
