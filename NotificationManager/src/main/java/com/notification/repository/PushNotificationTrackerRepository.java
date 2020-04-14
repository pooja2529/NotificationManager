package com.notification.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.notification.model.PushNotificationTracker;



public interface PushNotificationTrackerRepository extends CrudRepository<PushNotificationTracker, Integer>{

	 @Query(value = "select e.pn_to, e.message from PUSH_NOTIFICATION_TRACKER e",nativeQuery = true )
	    List<PushNotificationTracker> getMobileAndMessageOnly();
}
