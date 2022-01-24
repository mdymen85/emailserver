package com.bmc.emailserver.domain;

import java.util.Set;

import com.bmc.emailserver.mail.exception.IncorrectParameterException;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
public class MessageToSend {
	
	private String from;
	@Singular("recipient")
	private Set<String> recipients;
	private String subject;
	private String text;
	private String password;
	
	@Builder
	public MessageToSend(String from, Set<String> recipients, String subject, String text, String password) throws IncorrectParameterException {
		
		if (from == null || from.equals("")) {
			throw new IncorrectParameterException("from");
		}
		
		if (recipients == null || recipients.size() < 1) {
			throw new IncorrectParameterException("recipients");
		}
		
		if (subject == null || subject.equals("")) {
			throw new IncorrectParameterException("subjects");
		}
		
		if (text == null || text.equals("")) {
			throw new IncorrectParameterException("body");
		}
		
		this.from = from;
		this.recipients = recipients;
		this.subject = subject;
		this.text = text;
		this.password = password;
	}

	
}
