package com.bmc.emailserver.domain.mail.utilities;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bmc.emailserver.application.dto.MessageDTO;
import com.bmc.emailserver.domain.EmailMessage;
import com.bmc.emailserver.domain.LoadMessages;
import com.bmc.emailserver.domain.MessageToSend;
import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.domain.mail.exception.IncorrectHostException;
import com.bmc.emailserver.domain.mail.exception.IncorrectParameterException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailProcessorService {
	
	private static String HOST_REGEX = "(?<=@)\\\\S+";
	
	private String getHost(String email) {
		Pattern regex = Pattern.compile("(?<=@)\\S+");
		Matcher regexMatcher = regex.matcher(email);
		String host = "";
		
		if (regexMatcher.find()) {
			host = regexMatcher.group();			  
		} 
		
		return host;
		
	}
	
	public EmailMessage loadEmails(String email, String username, int page) throws InterruptedException, MessagingException, IOException, IllegalStateException, IncorrectHostException {
		var host = this.getHost(email);
		
		var emailHost = this.getEmailHost(host);
		
		User user = UserInformationService.getSingletonInstance().loadUserInformation(username);
		
		var loadMessages = this.toLoadMessages(email, user.getEmail(host).getPassword());
		
		return emailHost.getSendMail().load(loadMessages, page);
		
	}
	
	public LoadMessages toLoadMessages(String email, String password) {
		return LoadMessages
				.builder()
				.email(email)
				.password(password)
				.build();
	}

	public void sendMail(MessageDTO messageDTO, String username) throws AddressException, MessagingException, IllegalStateException, IOException, InterruptedException, IncorrectParameterException, IncorrectHostException {
				
		var host = this.getHost(messageDTO.getFrom());
		
		log.info("Host that will be used to send the email: {}", host);
		
		User user = UserInformationService.getSingletonInstance().loadUserInformation(username);
		
		log.info("User information recoverd: {}", user);
		
		var emailHost = this.getEmailHost(host);
		
		log.info("Getting information from host: {}.", host);
		
		var messageToSend = this.toMessageToSend(messageDTO, user.getEmail(host).getPassword());
		
		log.info("The message {} will be send.", messageToSend);
		
		emailHost.getSendMail().send(messageToSend);
	}
	
	private FactoryEmailHost getEmailHost(String host) throws IllegalStateException, IncorrectHostException {
		for (FactoryEmailHost emailHost : FactoryEmailHost.values()) {
			if (host.contains(emailHost.getHost())) {
				return emailHost;
			}
		}
		throw new IncorrectHostException(host);
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
