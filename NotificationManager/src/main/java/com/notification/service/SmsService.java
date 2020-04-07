package com.notification.service;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.notification.model.SmsTracker;

@Service
public class SmsService {

	
	 public  final String REST_SERVICE_URI = "http://localhost:8087/SpringBootRestApi/api";
	  
	  public  final String source="9136578317";
	  public  void sendSMS(List<String> mob,String message,int eventid,SmsTracker track)  {
	      
	        RestTemplate restTemplate = new RestTemplate();
	       // SmsTracker sms=new SmsTracker(1 , "9136578317", "hiiii", "send", "img.jpf", 1, 1, "", 2, "", "", "");
	    
	    	  

	        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/sendSms/",source,mob,message);
	        System.out.println("Location : "+uri.toASCIIString());
	      
	      
	    }

}
