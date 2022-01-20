package com.bmc.emailserver.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bmc.emailserver.YahooEmail;

@Path("/email")
public class SendController {

//	private final YahooEmail yahooEmail;
//	
//	@Inject
//	public SendController(YahooEmail yahooEmail) {
//		this.yahooEmail = yahooEmail;
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void get() {
		System.out.println("PASOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		//this.yahooEmail.send();
	}
	
}
