package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.PushNotificationMaster;
import com.notification.repository.PushNotificationMasterRepository;

@Service
public class PushNotificationMasterService {

	@Autowired
	PushNotificationMasterRepository pushRepo;
	
	public void addNotification(PushNotificationMaster notification) {
		
		pushRepo.save(notification);
	}

	public PushNotificationMaster getPushNotification(int pn_id) {
		// TODO Auto-generated method stub
		return pushRepo.findById(pn_id).orElse(null);
	}

}
