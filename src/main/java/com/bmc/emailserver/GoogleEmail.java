package com.bmc.emailserver;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleEmail {

	public void send() {
		   // Recipient's email ID needs to be mentioned.
        String to = "martin.dymenstein@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "martin.dymenstein@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties prop = new Properties();
 		prop.put("mail.smtp.host", "smtp.gmail.com");
         prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.auth", "true");
         prop.put("mail.smtp.starttls.enable", "true"); //TLS

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("martin.dymenstein@gmail.com", "xx");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("to_username_a@gmail.com, to_username_b@yahoo.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
	
}
