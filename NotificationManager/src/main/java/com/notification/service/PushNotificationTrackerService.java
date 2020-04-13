package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.PushNotificationTracker;
import com.notification.repository.PushNotificationTrackerRepository;

@Service
public class PushNotificationTrackerService {
	@Autowired
	PushNotificationTrackerRepository trackrepo;

	public void addNotification(PushNotificationTracker track) {
		// TODO Auto-generated method stub
		trackrepo.save(track);
		
	}



}
