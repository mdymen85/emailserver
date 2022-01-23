package com.bmc.emailserver;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class YahooEmail {

	public void send() {
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Yahoo */
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication()
                         {
                        	 return new PasswordAuthentication("martin.dymenstein@yahoo.com", "oejtgdmwxgjarjnp");
                             //return new PasswordAuthentication("bmcsoftware@yahoo.com.br", "oejtgdmwxgjarjnp");
                         }
                    });

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("martin.dymenstein@yahoo.com")); //Remetente

              message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse("martin.dymenstein@yahoo.com")); //Destinatário(s)
              message.setSubject("Enviando email com JavaMail");//Assunto
              message.setText("Enviei este email utilizando JavaMail com minha conta Yahoo!");
              /**Método para enviar a mensagem criada*/
              Transport.send(message);

              System.out.println("Feito!!!");

         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }    
	}
	
}
