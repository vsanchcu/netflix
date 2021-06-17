package com.vsanchcu.netflix.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class NetflixResponse implements Serializable {

	private static final long serialVersionUID = 4684359500265141278L;

	private final HttpStatus code;
	private final String status;
	private final String message;
	private Object data;

	public String getCode() {
		return String.valueOf(this.code);
	}

}
