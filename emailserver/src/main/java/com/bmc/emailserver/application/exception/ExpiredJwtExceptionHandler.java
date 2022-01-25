package com.bmc.emailserver.application.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExpiredJwtExceptionHandler implements ExceptionMapper<ExpiredJwtException>{

	@Override
	public Response toResponse(ExpiredJwtException exception) {
		return Response
				.status(exception.getStatus())
				.entity(exception.getErrorObject())
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
