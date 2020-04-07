package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.SmsMaster;
import com.notification.repository.SmsRepository;

@Service
public class SmsMasterService {
	
	@Autowired
	SmsRepository smsRepo;

	public SmsMaster getSmsTemplate(int sms_event_id) {
		return smsRepo.findById(sms_event_id).orElse(null);
	}

	
	
}
