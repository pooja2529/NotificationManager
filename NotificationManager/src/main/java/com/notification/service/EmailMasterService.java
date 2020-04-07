package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.EmailMaster;
import com.notification.repository.EmailRepository;

@Service
public class EmailMasterService 
{
	@Autowired
	EmailRepository templateRepo;
	
	public EmailMaster getTemplate(int templateid)
	{
		
		return templateRepo.findById(templateid).orElse(null);
		
	}
	
}
