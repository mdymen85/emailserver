package com.bmc.emailserver.domain.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.application.dto.MessageDTO;
import com.bmc.emailserver.domain.EmailMessage;
import com.bmc.emailserver.domain.mail.exception.IncorrectHostException;
import com.bmc.emailserver.domain.mail.exception.IncorrectParameterException;
import com.bmc.emailserver.domain.mail.utilities.EmailProcessorService;

public class SendMailService implements ISendMailService {
	
	private EmailProcessorService sendMail = new EmailProcessorService();
	
	@Override
	public void sendMail(MessageDTO messageDTO, String username) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException, IncorrectParameterException, IncorrectHostException {
		this.sendMail.sendMail(messageDTO, username);
		
	}

	@Override
	public EmailMessage loadMails(String email, String username, int page) throws InterruptedException, MessagingException, IOException, IllegalStateException, IncorrectParameterException, IncorrectHostException {
		return this.sendMail.loadEmails(email, username, page);		
	}

	
	
	
}
