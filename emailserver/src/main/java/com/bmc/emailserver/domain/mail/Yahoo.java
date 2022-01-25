package com.bmc.emailserver.domain.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import com.bmc.emailserver.domain.IEmailSession;
import com.bmc.emailserver.domain.LoadMessages;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Yahoo extends AbstractdMail {

	@Override
	public void loadProperties() throws IOException {
		var properties = this.getLoadProperties().loadProperties();
		
        Properties props = new Properties();

        var host = properties.getProperty("application.yahoo.mail.smtp.host");
        var port =  properties.getProperty("application.yahoo.mail.smtp.port");
        var starttls = properties.getProperty("application.yahoo.mail.smtp.starttls.enable");
        var auth = properties.getProperty("application.yahoo.mail.smtp.auth");
      
        log.info("Properties information for YAHOO, host: {}", host);
        log.info("Properties information for YAHOO, port: {}", port);
        log.info("Properties information for YAHOO, starttls: {}", starttls);
        log.info("Properties information for YAHOO, auth: {}", auth);
        
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.auth", auth);
		this.setProperties(props);
	}

	@Override
	public void loadSession(IEmailSession iEmailSession) {
		
		log.info("Loading email session for email: {}", iEmailSession.getEmail());
		
        Session session = Session.getDefaultInstance(this.getProperties(),
                new javax.mail.Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication()
                     {
                    	 return new PasswordAuthentication(iEmailSession.getEmail(), iEmailSession.getPassword());
                     }
                });

	    session.setDebug(true);
		this.setSession(session);
	}

	@Override
	protected void loadMessages(LoadMessages loadMessages) throws IOException, MessagingException {
	    Store store = this.getSession().getStore("imaps");
	    
		var properties = this.getLoadProperties().loadProperties();

	    store.connect(properties.getProperty("application.yahoo.imap.mail.yahoo.com"), loadMessages.getEmail(), loadMessages.getPassword());
	    
	    Folder emailFolder = store.getFolder("Inbox");
	    emailFolder.open(Folder.READ_ONLY);
		this.setFolder(emailFolder); 
	}

}
