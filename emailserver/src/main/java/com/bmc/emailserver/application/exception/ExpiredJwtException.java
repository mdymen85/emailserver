package com.bmc.emailserver.application.exception;

import javax.ws.rs.core.Response.Status;

@SuppressWarnings("serial")
public class ExpiredJwtException extends Exception implements ErrorEmailServer {

	private final ErrorObject errorObject;
	
	public ExpiredJwtException() {
		this.errorObject = ErrorObject
				.builder()
				.message("Token has expired...")
				.status(this.getStatus())
				.build();
	}
	
	@Override
	public Status getStatus() {
		return Status.FORBIDDEN;
	}

	@Override
	public ErrorObject getErrorObject() {
		return this.errorObject;
	}
	
	@Override
	public String getMessage() {
		return this.errorObject.getError();
	}

}
