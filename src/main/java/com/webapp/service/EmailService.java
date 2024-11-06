package com.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String to, String Subject, String message) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(to);
		smm.setSubject(Subject);
		smm.setText(message);
		javaMailSender.send(smm);
	}

}
