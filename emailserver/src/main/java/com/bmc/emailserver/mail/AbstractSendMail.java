package com.bmc.emailserver.mail;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.redisson.api.RFuture;
import org.redisson.api.RMapCache;

import com.bmc.emailserver.redis.RedisService;
import com.bmc.emailserver.redis.RedisSingleton;

import lombok.Getter;

@Getter
public abstract class AbstractSendMail {

	private Properties properties;
	private Session session;
	private LoadProperties loadProperties = new LoadProperties();

	protected abstract void loadProperties() throws IOException;
	protected abstract void loadSession(MessageToSend messageToSend);
	
	public Set<Message> loadMessages() throws InterruptedException, MessagingException, IOException {
		var redis = RedisSingleton.getRedisInstance();
		
		
		RMapCache<Object, Object> map = redis.redisson().getMapCache("XXX");
		var x = map.get("8");
		
		RFuture<Object> result = map.putAsync("8","9");
		result.await();
		
		map = redis.redisson().getMapCache("TestMap1");
		
	     Store store = session.getStore("imaps");

	      store.connect("imap.mail.yahoo.com","martin.dymenstein@yahoo.com","oejtgdmwxgjarjnp");

	      //create the folder object and open it
	      Folder emailFolder = store.getFolder("Inbox");
	      emailFolder.open(Folder.READ_ONLY);

	      // retrieve the messages from the folder in an array and print it
	      Message[] messages = emailFolder.getMessages();
	      System.out.println("messages.length---" + messages.length);

	      for (int i = 0, n = messages.length; i < n; i++) {
	         Message message = messages[i];
	         System.out.println("---------------------------------");
	         System.out.println("Email Number " + (i + 1));
	         System.out.println("Subject: " + message.getSubject());
	         System.out.println("From: " + message.getFrom()[0]);
	         System.out.println("Text: " + message.getContent().toString());

	      }
		return null;
	}
	
	public void send(MessageToSend messageToSend) throws AddressException, MessagingException, IOException, InterruptedException { 	
		
		this.loadProperties();
		this.loadSession(messageToSend);		
		
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
