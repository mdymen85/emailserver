package com.bmc.emailserver.mail;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.domain.MessageToSend;
import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.mail.exception.IncorrectParameterException;
import com.bmc.emailserver.redis.UserInformationService;

public class SendMail {
	
	private static String HOST_REGEX = "(?<=@)\\\\S+";

	public void sendMail(MessageDTO messageDTO, String username) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException, IncorrectParameterException {
		
		Pattern regex = Pattern.compile("(?<=@)\\S+");
		Matcher regexMatcher = regex.matcher(messageDTO.getFrom());
		String host = "";
		
		if (regexMatcher.find()) {
			host = regexMatcher.group();			  
		} 
		
		User user = UserInformationService.getSingletonInstance().loadUserInformation(username);
		
		var emailHost = this.getEmailHost(host);
		
		var messageToSend = this.toMessageToSend(messageDTO, user.getEmail(host).getPassword());
		
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
	
	private MessageToSend toMessageToSend(MessageDTO messageDTO, String password) throws IncorrectParameterException {		
		return MessageToSend
				.builder()
				.from(messageDTO.getFrom())
				.recipients(messageDTO.getTo())
				.subject(messageDTO.getSubject())
				.text(messageDTO.getBody())
				.password(password)
				.build();
	}
	
}
