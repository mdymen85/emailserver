package com.bmc.emailserver.mail.service;

import java.io.IOException;

import javax.interceptor.Interceptors;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.dto.MessageDTO;

public interface ISendMailService {

	public void sendMail(MessageDTO messageDTO, String username) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException;
	
}
