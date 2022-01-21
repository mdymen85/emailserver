package com.bmc.emailserver.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bmc.emailserver.YahooEmail;
import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.mail.SendMail;
import com.bmc.emailserver.mail.service.ISendMailService;
import com.bmc.emailserver.mail.service.SendMailService;

import lombok.extern.slf4j.Slf4j;

@Path("/email")
@javax.enterprise.context.RequestScoped
public class SendController {

	private YahooEmail yahooEmail = new YahooEmail();
	private ISendMailService sendMailService = new SendMailService();
//	
//	@Inject
//	public SendController(YahooEmail yahooEmail) {
//		this.yahooEmail = yahooEmail;
//	}
	
	public SendController() {}
	
	@GET
//	@Produces(MediaType.APPLICATION_JSON)
	public void get() {
	
		System.out.println("PASOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		this.yahooEmail.send();
	}
	
	@POST
	public void post(MessageDTO message) throws AddressException, MessagingException, IllegalStateException, IOException {
		sendMailService.sendMail(message);
		System.out.println(message);
		
	}
	
}
