package com.abs.scholarship.service;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.abs.scholarship.dto.MailRequest;

/**
 * Service for sending emails.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    @Async
    public void sendmail(MailRequest mailRequest) throws AddressException, MessagingException, IOException {
    	   Properties props = new Properties();
    	   props.put("mail.smtp.auth", "true");
    	   props.put("mail.smtp.starttls.enable", "true");
    	   props.put("mail.smtp.host", "smtp.gmail.com");
    	   props.put("mail.smtp.port", "587");
    	   
    	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    	      protected PasswordAuthentication getPasswordAuthentication() {
    	         return new PasswordAuthentication("abssocialinput@gmail.com", "mwcutgiawngtlmel");
    	      }
    	   });
    	   Message msg = new MimeMessage(session);
    	   msg.setFrom(new InternetAddress("abssocialinput@gmail.com", false));

    	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("abssocialinput@gmail.com"));
    	   msg.setSubject("Student Registration Details");
    	   if(mailRequest.getIsFirst()) {
    		   msg.setContent("Student Name : "+mailRequest.getStudentName()+"<br>Mobile Number : "+mailRequest.getNumber()
    		   	+"<br>Email : "+mailRequest.getEmail(), "text/html");
    	   }else {
    		   msg.setContent("Address/Area : "+mailRequest.getAddress()+"<br>Interested Course : "+mailRequest.getInterestedCourse()
   		   	+"<br>Father's Mobile Number : "+mailRequest.getFathersNumber() +"<br>Father's Occupation : "+mailRequest.getFathersOccupation()+"<br>Father's Annual Income : "+mailRequest.getFathersAnnualIncome(), "text/html");
    	   }
    	   msg.setSentDate(new Date());

			/*
			 * MimeBodyPart messageBodyPart = new MimeBodyPart();
			 * if(mailRequest.getIsFirst()) {
			 * messageBodyPart.setContent("Student Name : "+mailRequest.getStudentName()
			 * +"<br>Mobile Number : "+mailRequest.getNumber()
			 * +"<br>Email : "+mailRequest.getEmail(), "text/html"); }else {
			 * messageBodyPart.setContent("Address/Area : "+mailRequest.getAddress()
			 * +"<br>Interested Course : "+mailRequest.getInterestedCourse()
			 * +"<br>Father's Mobile Number : "+mailRequest.getFathersNumber()
			 * +"<br>Father's Occupation : "+mailRequest.getFathersOccupation()
			 * +"<br>Father's AnnualIncome : "+mailRequest.getFathersAnnualIncome(),
			 * "text/html"); }
			 */

    	   Multipart multipart = new MimeMultipart();
			/*
			 * multipart.addBodyPart(messageBodyPart); MimeBodyPart attachPart = new
			 * MimeBodyPart();
			 * 
			 * attachPart.attachFile("/var/tmp/image19.png");
			 * multipart.addBodyPart(attachPart); msg.setContent(multipart);
			 */
    	   Transport.send(msg);   
    	}


}


