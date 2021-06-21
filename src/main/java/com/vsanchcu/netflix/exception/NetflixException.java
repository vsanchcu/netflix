package com.vsanchcu.netflix.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class NetflixException extends Exception {

	private static final long serialVersionUID = -4918802495789160688L;

	private HttpStatus code;

	public NetflixException(final HttpStatus code, final String message) {
		super(message);
		this.code = code;
	}

}
