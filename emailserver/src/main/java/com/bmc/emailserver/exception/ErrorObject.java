package com.bmc.emailserver.exception;

import java.time.LocalDateTime;

import javax.ws.rs.core.Response.Status;

import lombok.Builder;
import lombok.Data;

@Data
public class ErrorObject {

	private final String timestamp;
	private final String error;
	private final int status;
	
	@Builder
	public ErrorObject(Long timestamp, String message, Status status) {
		this.timestamp = LocalDateTime.now().toString();
		this.error = message;
		this.status = status.getStatusCode();
	}

}
