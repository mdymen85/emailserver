package com.bmc.emailserver.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.bmc.emailserver.domain.MessageToSend;

public class SendYahoo extends AbstractSendMail {

	@Override
	public void loadProperties() throws IOException {
		var properties = this.getLoadProperties().loadProperties();
		
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Yahoo */
        props.put("mail.smtp.host", properties.getProperty("application.yahoo.mail.smtp.host"));
        props.put("mail.smtp.port", properties.getProperty("application.yahoo.mail.smtp.port"));
        props.put("mail.smtp.starttls.enable", properties.getProperty("application.yahoo.mail.smtp.starttls.enable"));
        props.put("mail.smtp.auth", properties.getProperty("application.yahoo.mail.smtp.auth"));
		this.setProperties(props);
	}

	@Override
	public void loadSession(MessageToSend messageToSend) {
        Session session = Session.getDefaultInstance(this.getProperties(),
                new javax.mail.Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication()
                     {
                    	 return new PasswordAuthentication(messageToSend.getFrom(), messageToSend.getPassword());
                     }
                });

	    session.setDebug(true);
		this.setSession(session);
	}

}
