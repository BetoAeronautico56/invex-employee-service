package com.invex.employee.exception;

public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1474971161139963333L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}