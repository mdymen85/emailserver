package com.bmc.emailserver.mail.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectParameterExceptionHandler implements ExceptionMapper<IncorrectParameterException> {

	@Override
	public Response toResponse(IncorrectParameterException exception) {
		return Response
				.status(exception.getStatus())
				.entity(exception.getErrorObject())
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
