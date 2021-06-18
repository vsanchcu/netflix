package com.vsanchcu.netflix.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vsanchcu.netflix.response.NetflixResponse;
import com.vsanchcu.netflix.util.ConstException;

@ControllerAdvice
public class NetflixExceptionHandler {

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public NetflixResponse unhandledErrors(HttpServletRequest req, Exception ex) {
		return new NetflixResponse(ConstException.ERROR, 
				HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}
	
	@ExceptionHandler({ NetflixException.class })
	@ResponseBody
	public NetflixResponse handleLmException(final HttpServletRequest request, 
			final HttpServletResponse response,
			final NetflixException ex) {
		response.setStatus(ex.getCode());
		return new NetflixResponse(ConstException.ERROR, ex.getCode(), ex.getMessage());
	}

}
