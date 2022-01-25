package com.bmc.emailserver.domain.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.application.dto.MessageDTO;
import com.bmc.emailserver.domain.EmailMessage;
import com.bmc.emailserver.domain.mail.exception.IncorrectHostException;
import com.bmc.emailserver.domain.mail.exception.IncorrectParameterException;

public interface ISendMailService {

	public void sendMail(MessageDTO messageDTO, String username) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException, IncorrectParameterException, IncorrectHostException;
	
	public EmailMessage loadMails(String email, String username, int page) throws InterruptedException, MessagingException, IOException, IncorrectParameterException, IncorrectHostException;
}
