package com.cg.bookmanagement.exception;

@SuppressWarnings("serial")
public class BookAlreadyExistException extends RuntimeException {

	public BookAlreadyExistException(String message) {
		super(message);
	}

	

}
