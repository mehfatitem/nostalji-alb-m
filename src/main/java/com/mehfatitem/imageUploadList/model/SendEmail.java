package com.mehfatitem.imageUploadList.model;

//File Name SendEmail.java
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    public boolean sendMail(String to, String from, String subject, String text) {
        Boolean returnResult = null;
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("temizfatih54@gmail.com", "****");
            }
        });
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            message.setContent(text, "text/html; charset=utf-8");

            // Now set the actual message
            //message.setText(text);
            // Send message
            Transport.send(message);
            returnResult = true;
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            returnResult = false;
        }
        return returnResult;
    }
}
