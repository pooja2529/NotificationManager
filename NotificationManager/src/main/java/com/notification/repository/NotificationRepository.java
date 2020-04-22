package com.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.notification.model.NotificationMaster;

public interface NotificationRepository extends JpaRepository<NotificationMaster, Integer>{

	@Query(value = "select content from notification_master where templateid=1 ",nativeQuery = true)
List<String> findContent();
	
	@Query(value = "select content from notification_master where templateid=?",nativeQuery = true)
	List<String> findSingleContent(@RequestParam int templateid );

}
