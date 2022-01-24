package com.bmc.emailserver.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.mail.exception.IncorrectParameterException;
import com.bmc.emailserver.mail.service.ISendMailService;
import com.bmc.emailserver.mail.service.SendMailService;


@Path("/email")
@Transactional
public class SendController {

	private ISendMailService sendMailService = new SendMailService();
	
	public SendController() {}
	
	
	private String getUsername(HttpHeaders headers) {
		var token = headers.getRequestHeaders().get("Authorization").get(0);
		return TokenUtils.getUsername(token);
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(MessageDTO message, @Context HttpHeaders headers) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException, IncorrectParameterException {
		
		var username = this.getUsername(headers); 
		
		sendMailService.sendMail(message, username);
		
		return Response
				.status(Status.CREATED)
				.build();
		
	}
	
	@GET
	@Path("/{email}/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response get(@Context UriInfo info, @Context HttpHeaders headers) throws InterruptedException, MessagingException, IOException {
		
		var username = this.getUsername(headers); 
		
		String email = info.getPathSegments().get(1).getPath();
		
		var page = 1;
		
		if (info.getPathSegments().size() > 2) {
			page = Integer.parseInt(info.getPathSegments().get(2).getPath());
		}
	
		
		var emails = sendMailService.loadMails(email, username, page);
		
		return Response
				.status(Status.OK)
				.entity(emails)
				.build();
	}
	
}
