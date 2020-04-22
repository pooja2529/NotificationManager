package com.notification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.model.User;
import com.notification.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	UserRepository userRepo;

	public User getById(int userid) {
		// TODO Auto-generated method stub
		return userRepo.findById(userid).orElse(null);
	}
	
	public List<String> getEmails(List<String> moblist) {
		// TODO Auto-generated method stub
		return userRepo.findAllEmails(moblist);
	}

	public List<String> getMob(List<Integer> uids) {
		// TODO Auto-generated method stub
		return userRepo.findAllMob(uids);
	}

	public List<String> getDeviceIds(List<Integer> uids) {
		// TODO Auto-generated method stub
		return userRepo.findByUserIds(uids);
	}

	public List<String> getDeviceId(List<String> moblist) {
		// TODO Auto-generated method stub
		return userRepo.findDeviceId(moblist);
	}

	public List<String> searchMob()
	{
		List<String> moblist=userRepo.findMobile();
		return moblist;
	}
	public List<User> getAllUser()
	{
		return (List<User>) userRepo.findAll();
	}
}
