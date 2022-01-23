package com.bmc.emailserver.mail;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.dto.MessageDTO;

public class SendMail {
	
	private static String HOST_REGEX = "(?<=@)\\\\S+";

	public void sendMail(MessageDTO messageDTO) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException {
		
		Pattern regex = Pattern.compile("(?<=@)\\S+");
		Matcher regexMatcher = regex.matcher(messageDTO.getFrom());
		String host = "";
		
		if (regexMatcher.find()) {
			host = regexMatcher.group();			  
		} 
		
		var emailHost = this.getEmailHost(host);
		
		var messageToSend = this.toMessageToSend(messageDTO);
		
		emailHost.getSendMail().send(messageToSend);
	}
	
	private FactoryEmailHost getEmailHost(String host) throws IllegalStateException {
		for (FactoryEmailHost emailHost : FactoryEmailHost.values()) {
			if (host.contains(emailHost.getHost())) {
				return emailHost;
			}
		}
		throw new IllegalStateException();
	}
	
	private MessageToSend toMessageToSend(MessageDTO messageDTO) {		
		return MessageToSend
				.builder()
				.from(messageDTO.getFrom())
				.recipients(messageDTO.getTo())
				.subject(messageDTO.getSubject())
				.text(messageDTO.getBody())
				.build();
	}
	
}
