package com.vsanchcu.netflix.exception;

import lombok.Getter;

@Getter
public abstract class NetflixException extends Exception {

	private static final long serialVersionUID = -4918802495789160688L;

	private int code;

	public NetflixException(final int code, final String message) {
		super(message);
		this.code = code;
	}

}
