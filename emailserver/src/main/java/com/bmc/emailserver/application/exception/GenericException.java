package com.bmc.emailserver.application.exception;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericException extends Exception implements ErrorEmailServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ErrorObject errorObject;
	
	public GenericException(Exception e) {
		this.errorObject = ErrorObject
				.builder()
				.message(e.getMessage())
				.status(this.getStatus())
				.build();
	}
	
	@Override
	public Status getStatus() {
		return Status.BAD_REQUEST;
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
