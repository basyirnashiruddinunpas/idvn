package org.masbas.idvn.helpers.exceptions;

public class UserAlreadyExistException extends RuntimeException {
	public UserAlreadyExistException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	
	public UserAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
	
}
