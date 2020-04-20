package com.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.notification.model.PushNotificationMaster;
import com.notification.model.PushNotificationTracker;
import com.notification.service.CustomErrorType;
import com.notification.service.PushNotificationMasterService;
import com.notification.service.PushNotificationService;
import com.notification.service.PushNotificationTrackerService;

@RestController
@RequestMapping("/RestCommunication")
public class PushController {
	@Autowired
	PushNotificationMasterService notificationService;
	
	@Autowired
	PushNotificationService pushService;

	@Autowired
	PushNotificationTrackerService notificationTrackService;
	
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public @ResponseBody ResponseEntity sendSimpleSms(@PathVariable("pn_id") int pn_id, List<String> userDeviceIdKey,
			PushNotificationTracker track) {
		try {
			PushNotificationMaster master = notificationService.getPushNotification(pn_id);
			track.setPn_title(master.getPn_title());
			track.setMessage(master.getMessage());
			track.setImage(master.getImage());
			track.setPn_sent_status("pn");
			notificationTrackService.addNotification(track);
			pushService.pushFCMNotification(userDeviceIdKey);
		} catch (Exception e) {
			// e.printStackTrace();
			return new ResponseEntity(new CustomErrorType("Fail", "Error....please check your code.." + e),
					HttpStatus.OK);
		}
		return new ResponseEntity(new CustomErrorType("Success", "Push Notifications sent!!"), HttpStatus.OK);

	}
}
