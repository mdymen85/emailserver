package com.bmc.emailserver.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.domain.EmailMessage;
import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.mail.exception.IncorrectParameterException;
import com.bmc.emailserver.mail.utilities.EmailProcessorService;

public class SendMailService implements ISendMailService {
	
	private EmailProcessorService sendMail = new EmailProcessorService();
	
	@Override
	public void sendMail(MessageDTO messageDTO, String sendMail) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException, IncorrectParameterException {
		this.sendMail.sendMail(messageDTO, sendMail);
		
	}

	@Override
	public EmailMessage loadMails(String email, String username, int page) throws InterruptedException, MessagingException, IOException {
		return this.sendMail.loadEmails(email, username, page);		
	}

	
	
	
}
