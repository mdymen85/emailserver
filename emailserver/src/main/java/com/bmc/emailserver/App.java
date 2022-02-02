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

import com.bmc.emailserver.application.controller.RestApplication;


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
	
}
