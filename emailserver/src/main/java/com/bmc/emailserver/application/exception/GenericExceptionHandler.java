package com.bmc.emailserver.application.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<GenericException>{

	@Override
	public Response toResponse(GenericException exception) {
		return Response
				.status(exception.getStatus())
				.entity(exception.getErrorObject())
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
