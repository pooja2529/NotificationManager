package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.SmsTracker;
import com.notification.repository.SmsTrackerRepository;

@Service
public class SmsTrackerService {
	@Autowired
	SmsTrackerRepository smsTrackRepo;

	public SmsTracker addSmsTrack(SmsTracker smsTrack)
	{
		return smsTrackRepo.save(smsTrack);
	}
	
}
