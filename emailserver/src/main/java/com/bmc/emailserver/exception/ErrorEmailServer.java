package com.bmc.emailserver.exception;

import javax.ws.rs.core.Response.Status;

public interface ErrorEmailServer {

	public Status getStatus();
	public ErrorObject getErrorObject();
	
}
