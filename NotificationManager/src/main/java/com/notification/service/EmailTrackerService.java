package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.EmailTracker;
import com.notification.repository.EmailTrackerRepository;

@Service
public class EmailTrackerService 
{

	@Autowired
	EmailTrackerRepository emailTrackRepo;
	
	
	public EmailTracker addEmailNotification(EmailTracker tracker)
	{
		return emailTrackRepo.save(tracker);
	}
}

