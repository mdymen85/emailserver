package com.bmc.emailserver.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.mail.SendMail;

import lombok.extern.slf4j.Slf4j;

public class SendMailService implements ISendMailService {
	
	private SendMail sendMail = new SendMail();
	
	@Override
	public void sendMail(MessageDTO messageDTO) throws AddressException, MessagingException, IllegalStateException, IOException {
		this.sendMail.sendMail(messageDTO);
		
	}

	
	
	
}
