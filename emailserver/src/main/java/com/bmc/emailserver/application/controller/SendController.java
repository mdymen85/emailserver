package com.bmc.emailserver.application.controller;

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

import org.glassfish.jersey.server.internal.process.MappableException;

import com.bmc.emailserver.application.dto.MessageDTO;
import com.bmc.emailserver.application.exception.ExpiredJwtException;
import com.bmc.emailserver.domain.mail.exception.IncorrectHostException;
import com.bmc.emailserver.domain.mail.exception.IncorrectParameterException;
import com.bmc.emailserver.domain.mail.service.ISendMailService;
import com.bmc.emailserver.domain.mail.service.SendMailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/email")
@Transactional
@SneakyThrows
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
	public Response post(MessageDTO message, @Context HttpHeaders headers) throws Exception {
		try {
			var username = this.getUsername(headers); 
			
			log.info("Starting process of sending an email for the user {}", username);
			log.info("Email information: {}", message);
			
			sendMailService.sendMail(message, username);
			
			log.info("Sending email done.");
			
			return Response
					.status(Status.CREATED)
					.build();
		}
		catch (Exception e) {
			log.error("error: {} ", e.getStackTrace());
			throw e;			
		}
	}
	
	@GET
	@Path("/{email}/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response get(@Context UriInfo info, @Context HttpHeaders headers) throws Exception {
		try {
			
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
		catch (Exception e) {
			log.error("error: {} ", e.getStackTrace());
			throw e;
		}
	}
	
}
