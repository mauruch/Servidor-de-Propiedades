package com.mauruch.propertyserver.exceptions;

public class UnsupportedCommandException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UnsupportedCommandException(String msg) {
		super(msg);
	}

}
