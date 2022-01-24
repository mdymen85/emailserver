package com.bmc.emailserver.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
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

import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.dto.Usuario;
import com.bmc.emailserver.exception.ErrorObject;
import com.bmc.emailserver.mail.SendMail;
import com.bmc.emailserver.mail.exception.IncorrectParameterException;
import com.bmc.emailserver.mail.service.ISendMailService;
import com.bmc.emailserver.mail.service.SendMailService;


@Path("/email")
//@javax.enterprise.context.RequestScoped
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
				.status(201)
				.build();
		
	}
	
}
