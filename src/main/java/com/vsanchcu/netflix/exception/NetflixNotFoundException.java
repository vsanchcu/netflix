package com.vsanchcu.netflix.exception;

import org.springframework.http.HttpStatus;

public class NetflixNotFoundException extends NetflixException {

	private static final long serialVersionUID = 3941005390621225481L;

	public NetflixNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

}
