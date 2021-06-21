package com.vsanchcu.netflix.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class NetflixResponse<T> implements Serializable {

	private static final long serialVersionUID = 4684359500265141278L;

	@ApiModelProperty(position = 0)
	@NonNull
	private String status;
	
	@ApiModelProperty(position = 1)
	@NonNull
	private HttpStatus code;
	
	@ApiModelProperty(position = 2)
	@NonNull
	private String message;
	
	@ApiModelProperty(position = 3)
	private T data;

	public String getCode() {
		return String.valueOf(this.code);
	}

}
