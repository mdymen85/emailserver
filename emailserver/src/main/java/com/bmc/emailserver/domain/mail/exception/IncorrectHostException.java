package com.bmc.emailserver.domain.mail.exception;

import javax.ws.rs.core.Response.Status;

import com.bmc.emailserver.domain.exception.ErrorEmailServer;
import com.bmc.emailserver.domain.exception.ErrorObject;

@SuppressWarnings("serial")
public class IncorrectHostException extends Exception implements ErrorEmailServer{

	private final ErrorObject errorObject;
	
	public IncorrectHostException(String host) {
		this.errorObject = ErrorObject
				.builder()
				.message(String.format("The host: %s is not available in the application. Talk to Dvir.", host))
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
