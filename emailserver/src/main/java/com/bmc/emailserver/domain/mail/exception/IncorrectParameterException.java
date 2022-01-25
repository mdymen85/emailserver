package com.bmc.emailserver.domain.mail.exception;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.bmc.emailserver.domain.exception.ErrorEmailServer;
import com.bmc.emailserver.domain.exception.ErrorObject;

@Provider
public class IncorrectParameterException extends Exception implements ErrorEmailServer {
	
	private final ErrorObject errorObject;
	
	private static final long serialVersionUID = 1L;
	
	public IncorrectParameterException(String field) {
		this.errorObject = ErrorObject
					.builder()
					.message("Incorrect value for field: " + field)
					.status(this.getStatus())
					.build();
	}

	@Override
	public Status getStatus() {
		return Status.BAD_REQUEST;
	}
	
	@Override
	public String getMessage() {
		return this.errorObject.getError();
	}

	@Override
	public ErrorObject getErrorObject() {
		return this.errorObject;
	}

}
