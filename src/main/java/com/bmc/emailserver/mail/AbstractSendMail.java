package com.bmc.emailserver.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.Getter;

@Getter
public abstract class AbstractSendMail {

	private Properties properties;
	private Session session;
	private LoadProperties loadProperties = new LoadProperties();

	protected abstract void loadProperties() throws IOException;
	protected abstract void loadSession();
	
	public void send(MessageToSend messageToSend) throws AddressException, MessagingException, IOException {
 		this.loadProperties();
		this.loadSession();
		
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(messageToSend.getFrom())); //Remetente
        
        String recipients = messageToSend.getRecipients().toString().replace("[","").replace("]", "");

        message.setRecipients(Message.RecipientType.TO,
                          InternetAddress.parse(recipients)); //Destinatário(s)
        
        message.setSubject(messageToSend.getSubject());//Assunto
        message.setText(messageToSend.getText());
        
        /**Método para enviar a mensagem criada*/
        Transport.send(message);
		
		
	}
	
	protected void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	protected void setSession(Session session) {
		this.session = session;
		
	}
	
	protected LoadProperties getLoadProperties() {
		return this.loadProperties;
	}
	
}
