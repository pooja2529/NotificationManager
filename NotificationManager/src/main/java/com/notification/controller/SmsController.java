package com.notification.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.notification.model.SmsMaster;
import com.notification.model.SmsTracker;
import com.notification.service.CustomErrorType;
import com.notification.service.SmsMasterService;
import com.notification.service.SmsService;
import com.notification.service.SmsTrackerService;

@RestController
public class SmsController {

	@Autowired
	SmsService smsService;
	
	@Autowired
	SmsMasterService smsMasterService;
	
	@Autowired
	SmsTrackerService smsTrackservice;
	
	@SuppressWarnings("rawtypes")
	public @ResponseBody ResponseEntity sendSimpleSms(List<String> mob,String message,int eventid,SmsTracker track)
	{
try {
		SmsMaster sms=smsMasterService.getSmsTemplate(eventid);
        track.setSmsstatus("PN");
        track.setCreatedby(sms.getCreatedby());
        track.setModifiedby(sms.getModifiedby());
        track.setSmstemplate(sms.getSms_template());
        track.setCreateddatetime(new Date());
        for (String smsto : mob) {
        	 track.setSmsto(smsto);
		}
       
        smsTrackservice.addSmsTrack(track);
        smsService.sendSMS(mob, message, eventid, track);
       
        if(track.getSmsstatus()=="PN")
        {
        	 track.setSmsstatus("Y");
        	 smsTrackservice.addSmsTrack(track);
        }
}
catch (Exception e) {
	return new ResponseEntity<>(new CustomErrorType("Fail", "Unable to sent sms"), HttpStatus.INTERNAL_SERVER_ERROR);
}
return new ResponseEntity<>(new CustomErrorType("Success", "Please check your Message box!!!!"), HttpStatus.OK);
       
       
	}
}
