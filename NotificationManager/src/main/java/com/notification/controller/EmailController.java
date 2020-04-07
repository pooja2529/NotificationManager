package com.notification.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.notification.model.EmailMaster;
import com.notification.model.EmailTracker;
import com.notification.service.CustomErrorType;
import com.notification.service.EmailService;
import com.notification.service.EmailTrackerService;
import com.notification.service.EmailMasterService;

@RestController
public class EmailController 
{
	@Autowired
	EmailService emailService;

	@Autowired
	EmailMasterService emailMasterService;
	
	@Autowired
	EmailTrackerService emailtrackerservice;


	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/simple-email/{user-email}")
	public @ResponseBody ResponseEntity sendSimpleEmail(@PathVariable("user-email") List<String> email,@PathVariable("templateid")int id,EmailTracker emailTrack)
	{

		try {
			
			EmailMaster notificationContent=emailMasterService.getTemplate(id);
			if(notificationContent.getSubject()!=null && notificationContent.getBody()!=null)
			{
				emailTrack.setSubject(notificationContent.getSubject());
				emailTrack.setBody(notificationContent.getBody());
				emailTrack.setCreateddatetime(new Date());
				emailTrack.setSentstatus("pn");
				emailTrack.setEmailfrom(notificationContent.getEmailfrom());
				emailTrack.setCreatedby(notificationContent.getCreatedby());
				emailTrack.setModifiedby(notificationContent.getCreatedby());
				for (String string : email) {
					emailTrack.setEmailto(string);
				}
			}
			else
			{
				return new ResponseEntity<>(new CustomErrorType("Fail", "Please Check your data ..notification is NULL in table(NOTIFCATION_MASTER)"), HttpStatus.OK);
			}
			
			
			
			emailtrackerservice.addEmailNotification(emailTrack);
			emailService.sendSimpleEmail(id,email,notificationContent.getSubject(),notificationContent.getBody());
			if(emailTrack.getSentstatus()=="pn")
			{
				
	
				emailTrack.setSentstatus("Y");
				emailTrack.setModifieddatetime(new Date());
				emailtrackerservice.addEmailNotification(emailTrack);
			}
			else
			{
				
				emailTrack.setSentstatus("N");
				emailTrack.setModifieddatetime(new Date());
				emailtrackerservice.addEmailNotification(emailTrack);
			}
			
		} catch (MailException mailException) {
			//  LOG.error("Error while sending out email..{}", mailException.getStackTrace());
			System.out.println(mailException );
			return new ResponseEntity<>(new CustomErrorType("Fail", "Unable to sent email"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new CustomErrorType("Success", "Please check your mail box!!!!"), HttpStatus.OK);
	}


}
