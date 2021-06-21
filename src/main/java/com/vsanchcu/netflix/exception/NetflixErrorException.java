package com.vsanchcu.netflix.exception;

import org.springframework.http.HttpStatus;

public class NetflixErrorException extends NetflixException {

	private static final long serialVersionUID = -81734225324011573L;

	public NetflixErrorException(HttpStatus code, String message) {
		super(code, message);
	}

}
