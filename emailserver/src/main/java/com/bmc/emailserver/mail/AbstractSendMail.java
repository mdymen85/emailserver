package com.bmc.emailserver.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.bmc.emailserver.domain.EmailMessage;
import com.bmc.emailserver.domain.IEmailSession;
import com.bmc.emailserver.domain.LoadMessages;
import com.bmc.emailserver.domain.MessageToSend;
import lombok.Getter;

@Getter
public abstract class AbstractSendMail {

	private Properties properties;
	private Session session;
	private Folder folder;
	private LoadProperties loadProperties = new LoadProperties();

	protected abstract void loadProperties() throws IOException;
	protected abstract void loadSession(IEmailSession iEmailSession);
	protected abstract void loadMessages(LoadMessages loadMessages) throws IOException, MessagingException ;
	
	public EmailMessage load(LoadMessages loadMessages, int page) throws InterruptedException, MessagingException, IOException {

		var properties = this.getLoadProperties().loadProperties();

		Integer quantityPerPage = Integer.parseInt(properties.getProperty("application.max-load.emails"));

		var wrapperPage = new Page(quantityPerPage, page);	
		
		this.loadProperties();
		this.loadSession(loadMessages);	
		this.loadMessages(loadMessages);
		
	    Message[] messages = this.folder.getMessages(wrapperPage.getInit(), wrapperPage.getTerminate());

		return toEmailMessage(messages, loadMessages);
	}
	
	private com.bmc.emailserver.domain.Message toMessage(Message message) throws MessagingException, IOException {
		var newMessage = com.bmc.emailserver.domain.Message
				.builder()
				.from(message.getFrom()[0].toString())
				.body(message.getContent().toString())
				.subject(message.getSubject())
				.build();
		
		return newMessage;
				
	}
	
	private EmailMessage toEmailMessage(Message[] messages, LoadMessages loadMessages) throws MessagingException, IOException {

		List<com.bmc.emailserver.domain.Message> newMessages = new ArrayList<com.bmc.emailserver.domain.Message>(messages.length);
		for (Message m : messages) {
			newMessages.add(this.toMessage(m));
		}

		var emailMessage = EmailMessage
				.builder()
				.email(loadMessages.getEmail())
				.username("")
				.messages(newMessages)
				.build();
		
		
		return emailMessage;
	}
	
	public void send(MessageToSend messageToSend) throws AddressException, MessagingException, IOException, InterruptedException { 	
		
		this.loadProperties();
		this.loadSession(messageToSend);		
		
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(messageToSend.getFrom()));
        
        String recipients = messageToSend.getRecipients().toString().replace("[","").replace("]", "");

        message.setRecipients(Message.RecipientType.TO,
                          InternetAddress.parse(recipients));
        
        message.setSubject(messageToSend.getSubject());
        message.setText(messageToSend.getText());
        
        Transport.send(message);
		
		
	}
	
	protected void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	protected void setSession(Session session) {
		this.session = session;
		
	}
	
	protected void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	protected LoadProperties getLoadProperties() {
		return this.loadProperties;
	}
	
	private class Page {
		private int init;
		private int terminate;
		
		public Page(Integer quantityPerPage, int page) {
			int initialPage = 1;
			if (page > 1) {
				initialPage = (page-1)*quantityPerPage;
			}
			this.init = initialPage;
			this.terminate = page*quantityPerPage;
		}
		
		public int getInit() {
			return this.init;
		}
		
		public int getTerminate() {
			return this.terminate;
		}
	}
	
}
