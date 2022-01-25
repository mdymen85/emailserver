package com.bmc.emailserver.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import lombok.Builder;
import lombok.ToString;

@ToString
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
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
		if (this.emails == null) {
			this.emails = new HashSet<Email>();
		}
		this.emails.add(email);
		email.setUser(this);
	}
	
	public Email getEmail(String host) {
		Iterator<Email> itEmails = emails.iterator();
		
		while(itEmails.hasNext()) {
			var actualEmail = itEmails.next();
			if (actualEmail.getEmail().contains(host)) {
				return actualEmail;
			}
		}
		
		throw new IllegalStateException();
	}
	
}
