package com.bmc.emailserver.domain;

import java.util.Iterator;
import java.util.Set;

import lombok.Builder;

public class User {

	private final String username;
	private Set<Email> emails;

	@Builder
	public User(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void addEmail(Email email) {
		this.emails.add(email);
		email.setUser(this);
	}
	
	public Email getEmail(String host) {
		Iterator<Email> itEmails = emails.iterator();
		
		while(itEmails.hasNext()) {
			var actualEmail = itEmails.next();
			if (host.equals(actualEmail.getEmail())) {
				return actualEmail;
			}
		}
		
		throw new IllegalStateException();
	}
	
}
