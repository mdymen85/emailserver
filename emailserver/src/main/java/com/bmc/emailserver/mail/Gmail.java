package com.bmc.emailserver.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.bmc.emailserver.domain.IEmailSession;
import com.bmc.emailserver.domain.LoadMessages;

public class Gmail extends AbstractdMail {

	@Override
	public void loadProperties() throws IOException {
		var properties = this.getLoadProperties().loadProperties();
		
        Properties props = new Properties();
        
        props.put("mail.smtp.host", properties.getProperty("application.gmail.smtp.host"));
        props.put("mail.smtp.port", properties.getProperty("application.gmail.smtp.port"));
        props.put("mail.smtp.starttls.enable", properties.getProperty("application.gmail.smtp.starttls.enable"));
        props.put("mail.smtp.auth", properties.getProperty("application.gmail.smtp.auth"));
        
        this.setProperties(props);
		
	}

	@Override
	public void loadSession(IEmailSession iEmailSession) {
		//https://myaccount.google.com/lesssecureapps		
		
        Session session = Session.getInstance(this.getProperties(), new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(iEmailSession.getEmail(), iEmailSession.getPassword());

            }

        });

        session.setDebug(true);
		this.setSession(session);
	}

	@Override
	protected void loadMessages(LoadMessages loadMessages) {
		// TODO Auto-generated method stub
		
	}

}
