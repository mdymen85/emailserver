package com.bmc.emailserver.domain.mail.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncorrectHostExceptionHandler implements ExceptionMapper<IncorrectHostException> {

	@Override
	public Response toResponse(IncorrectHostException exception) {		
		
		return Response
				.status(exception.getStatus())
				.entity(exception.getErrorObject())
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
