package com.notification.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.notification.model.PushNotificationTracker;



public interface PushNotificationTrackerRepository extends CrudRepository<PushNotificationTracker, Integer>{

	 @Query(value = "select e.pn_to, e.message from PUSH_NOTIFICATION_TRACKER e",nativeQuery = true )
	    List<PushNotificationTracker> getMobileAndMessageOnly();
	 
	 @Query(value = "SELECT p.pn_to FROM testdb.push_notification_tracker p WHERE p.CREATED_DATE_TIME >= DATE_SUB(NOW(),INTERVAL 15 MINUTE)",nativeQuery = true)
	 public List<String> fetch();
}
