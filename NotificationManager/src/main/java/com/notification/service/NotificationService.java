package com.notification.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.NotificationMaster;
import com.notification.repository.NotificationRepository;

@Service
public class NotificationService 
{
	@Autowired
NotificationRepository notificationRepository;
	
	public NotificationMaster getNotification(int notificationId) 
	{
		return  notificationRepository.findById(notificationId).orElse(null);
	}
	
	public Map<Integer,NotificationMaster> getAvailableNotification(int id)
	{
		Map<Integer,NotificationMaster> result=new HashMap<Integer, NotificationMaster>();
		
		NotificationMaster validNotification=getNotification(id);
		
		result.put(validNotification.getNotificationId(), validNotification);
		
		return result;
		
	}
	
	
}
