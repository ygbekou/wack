package com.wack.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
 
@Component("myMailSender")
public class MyMailSender {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void sendMail(String from, String[] to, String subject, String body) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
 
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		
		logger.info("Sending...");
		
		javaMailSender.send(mail);
		
		logger.info("Done!");
	}
	
	
	public void sendMimeMail(String from, String[] receiverEmails, String subject, String body) {

		
		InternetAddress[] parsed;
		StringBuilder receiverEmailsBuilder = new StringBuilder();
	    try {
	    	for (int i = 0; i < receiverEmails.length; i++) {	
	    		receiverEmailsBuilder.append(receiverEmails[i]);
	    		if (i < receiverEmails.length - 1) {
	    			receiverEmailsBuilder.append(",");
	    		}
	    	}
	    	
	    	parsed = InternetAddress.parse(receiverEmailsBuilder.toString());
		    MimeMessage mailMessage = javaMailSender.createMimeMessage();
		    mailMessage.setSubject(subject, "UTF-8");
		      
		    MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		    helper.setFrom(from);
		    helper.setTo(parsed);
		    helper.setText(body, true);
	
			logger.info("Sending...");
	
			javaMailSender.send(mailMessage);
	
			logger.info("Done!");
		
	    } catch (AddressException e) {
	    	throw new IllegalArgumentException("Not valid email: " +receiverEmails , e);
	    } catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}