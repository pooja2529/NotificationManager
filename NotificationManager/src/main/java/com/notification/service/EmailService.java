package com.notification.service;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
	@Autowired
	private JavaMailSender javaMailSender;


	public void sendSimpleEmail(int id,List<String> toAddress, String subject, String temp_body) {

		final MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
			helper.setSubject(subject);
			helper.setText(temp_body, true);
			for (String to : toAddress) {
				helper.addTo(to);
			}
		} catch (MessagingException e) {
			throw new MailParseException(e);

		}
		javaMailSender.send(message);

	}
	public void sendSimpleEmailWithAttachment(int id,String toAddress, String subject, String temp_body,String attachment) {

		final MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(temp_body, true);
			FileSystemResource file = new FileSystemResource(new File(attachment));
			helper.addAttachment("Tasksheet", file);
		
		} catch (MessagingException e) {
			throw new MailParseException(e);

		}
		javaMailSender.send(message);

	}
	

}
