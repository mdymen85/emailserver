package com.bmc.emailserver.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.domain.EmailMessage;
import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.mail.exception.IncorrectParameterException;

public interface ISendMailService {

	public void sendMail(MessageDTO messageDTO, String username) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException, IncorrectParameterException;
	
	public EmailMessage loadMails(String email, String username, int page) throws InterruptedException, MessagingException, IOException;
}
