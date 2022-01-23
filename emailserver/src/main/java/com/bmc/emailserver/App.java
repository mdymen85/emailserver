package com.bmc.emailserver;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
//import java.util.Properties;
//
//import javax.mail.Folder;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Store;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.bmc.emailserver.controller.RestApplication;


/**
 * Hello world!
 *
 */
public class App 
{
	

	  private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://localhost/").port(8081).build();
	  }

	  static final URI BASE_URI = getBaseURI();

	  static HttpServer startServer() {
	    ResourceConfig rc = ResourceConfig.forApplication(new RestApplication());
	    return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
	  }

	  public static void main(String[] args) throws IOException {
	    System.out.println("Starting grizzly...");
	    HttpServer httpServer = startServer();
	    System.out.printf("Jersey app started with WADL available at %sapplication.wadl%n", BASE_URI);
	    System.out.println("Hit enter to stop it...");
	    System.in.read();
	    httpServer.shutdownNow();
	  }
	
//	private synchronized void waitMethod() {
//		 
//		while (true) {
//			System.out.println("always running program ==> " + Calendar.getInstance().getTime());
//			try {
//				this.wait(2000);
//			} catch (InterruptedException e) {
// 
//				e.printStackTrace();
//			}
//		}
// 
//	}
//	
//    public static void main( String[] args ) throws IOException
//    {
//        System.out.println( "Hello World!" );
//        
//        App object = new App();
//        
//        object.waitMethod();
//        
////        Properties props = new Properties();
////        /** Parâmetros de conexão com servidor Yahoo */
////        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
////        props.put("mail.smtp.port", "587");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.smtp.auth", "true");
////
////        Session session = Session.getDefaultInstance(props,
////                    new javax.mail.Authenticator() {
////                         protected PasswordAuthentication getPasswordAuthentication()
////                         {
////                        	 return new PasswordAuthentication("martin.dymenstein@yahoo.com", "oejtgdmwxgjarjnp");
////                             //return new PasswordAuthentication("bmcsoftware@yahoo.com.br", "oejtgdmwxgjarjnp");
////                         }
////                    });
////
////        /** Ativa Debug para sessão */
////        session.setDebug(true);
////
////        try {
////        	
////        	
////        	Store store = session.getStore("imaps");
////        	store.connect("imap.mail.yahoo.com", "martin.dymenstein@yahoo.com", "oejtgdmwxgjarjnp");
////        	System.out.println(store);
////        	
////        	
////        	Folder inbox = store.getFolder("Inbox");
////        	inbox.open(Folder.READ_ONLY);
////        	Message messages[] = inbox.getMessages();
////        	for(Message message:messages) {
////        		System.out.println(message.getContent());
////        		System.out.println(message); // com.sun.mail.imap.IMAPInputStream@cec0c5
////        	}
//////              Message message = new MimeMessage(session);
//////              message.setFrom(new InternetAddress("martin.dymenstein@yahoo.com")); //Remetente
//////
//////              message.setRecipients(Message.RecipientType.TO,
//////                                InternetAddress.parse("martin.dymenstein@yahoo.com")); //Destinatário(s)
//////              message.setSubject("Enviando email com JavaMail");//Assunto
//////              message.setText("Enviei este email utilizando JavaMail com minha conta Yahoo!");
//////              /**Método para enviar a mensagem criada*/
//////              Transport.send(message);
////
////              System.out.println("Feito!!!");
////
////         } catch (MessagingException e) {
////              throw new RuntimeException(e);
////        }    
//
//    }
//    
}
