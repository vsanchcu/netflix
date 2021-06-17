package com.vsanchcu.netflix.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NetflixException extends Exception {

	private static final long serialVersionUID = -4918802495789160688L;

	private int code;
	private String message;

}
