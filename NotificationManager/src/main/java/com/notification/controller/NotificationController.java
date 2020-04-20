package com.notification.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.notification.model.EmailTracker;
import com.notification.model.Notification;
import com.notification.model.NotificationMaster;
import com.notification.model.PushNotificationMaster;
import com.notification.model.PushNotificationTracker;
import com.notification.model.SmsMaster;
import com.notification.model.SmsTracker;
import com.notification.model.User;
import com.notification.service.CustomErrorType;
import com.notification.service.NotificationService;
import com.notification.service.PushNotificationMasterService;
import com.notification.service.SmsMasterService;
import com.notification.service.UserService;
import com.notification.util.CommonValidator;

@RestController
@RequestMapping("/Notification")
public class NotificationController 
{
	@Autowired
	NotificationService notificationService;

	@Autowired
	UserService userService;

	@Autowired
	CommonValidator validator;

	@Autowired
	EmailController emailService;

	@Autowired
	SmsMasterService smsMasterService;

	@Autowired
	SmsController smsService;

	@Autowired
	PushController pushService;

	@Autowired
	PushNotificationMasterService pushmasterservice;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/verifyUser/")
	public ResponseEntity<?> verifyNotification(@RequestBody  Notification notification,User user,EmailTracker emailTrack,SmsTracker track,PushNotificationTracker pushtrack) 
	{


		int id=notification.getMasterid();
		String prod=notification.getProduct();
		String type=notification.getNotificationtype();
		List<String> mob=notification.getMobile();
		List<Integer> uid=notification.getUserid();
		//List<String> email=notification.getEmail();

		try
		{
			if(validator.isValidMobileNumber(mob))
			{
				if(validator.isValidString(type))
				{
					if(validator.isValidString(prod))
					{
						Map<Integer,NotificationMaster> result=notificationService.getAvailableNotification(notification.getMasterid());

						if(prod.equalsIgnoreCase(result.get(id).getProduct()) && type.equalsIgnoreCase(result.get(id).getNotificationType()))
						{
							List<String> moblist=userService.getMob(uid);	

							if(mob.equals(moblist))
							{
								if(type.equalsIgnoreCase("email"))
								{
									List<String> emaillist=userService.getEmails(mob);
									
									emailService.sendSimpleEmail(emaillist,result.get(id).getTemplateid(), emailTrack);
									return new ResponseEntity(new CustomErrorType("Success","Email Notification is sent !!!!!!"),HttpStatus.OK);
								}
								else if(type.equalsIgnoreCase("sms"))
								{
									System.out.println("sms code");
									SmsMaster sms=smsMasterService.getSmsTemplate(result.get(id).getTemplateid());
									smsService.sendSimpleSms(moblist ,sms.getSms_template() ,sms.getSms_event_id(),track );
									return new ResponseEntity(new CustomErrorType("Success","SMS Notification is sent !!!!!!"),HttpStatus.OK);
								}
								else if(type.equalsIgnoreCase("pn"))
								{
									System.out.println("push notification code");
									PushNotificationMaster master=pushmasterservice.getPushNotification(result.get(id).getTemplateid());
									List<String> userDeviceID=userService.getDeviceIds(uid);
									pushService.sendSimpleSms(master.getPn_id(),userDeviceID,pushtrack);
									return new ResponseEntity(new CustomErrorType("Success","Push Notification is sent !!!!!!"),HttpStatus.OK);
								}

							}
							else
							{
								return new ResponseEntity(new CustomErrorType("Fail","Mobile Number is not registered"),HttpStatus.OK);
							}
						}
						return new ResponseEntity(new CustomErrorType("Fail","Notification is not verified"),HttpStatus.OK);
					}
					return new ResponseEntity(new CustomErrorType("Fail","Product  is not valid"),HttpStatus.OK);
				}
				return new ResponseEntity(new CustomErrorType("Fail","Notification Type is not valid"),HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity(new CustomErrorType("Fail","Mobile number is not valid"),HttpStatus.OK);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new CustomErrorType("Fail","Error....please check your code.."+e),HttpStatus.OK);
		}


	}



}
