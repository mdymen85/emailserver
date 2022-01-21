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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
import com.bmc.emailserver.YahooEmail;
import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.dto.Usuario;
import com.bmc.emailserver.mail.SendMail;
import com.bmc.emailserver.mail.service.ISendMailService;
import com.bmc.emailserver.mail.service.SendMailService;


@Path("/email")
@javax.enterprise.context.RequestScoped
@Transactional
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

	@POST
	@Path("/x")
	public void post2(MessageDTO message) throws Exception {
		
		System.out.println("asfafsdf");
		
//		try {
//		    Algorithm algorithm = Algorithm.HMAC256("secret");
//		    String token = JWT.create()
//		        .withIssuer("auth0")
//		        .sign(algorithm);
//		    
//		    return token;
//		} catch (JWTCreationException exception){
//		    //Invalid Signing configuration / Couldn't convert Claims.
//			throw new Exception();
//		}
		
	}
}
