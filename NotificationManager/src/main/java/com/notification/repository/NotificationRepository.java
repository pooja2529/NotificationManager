package com.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.notification.model.NotificationMaster;

public interface NotificationRepository extends JpaRepository<NotificationMaster, Integer>{

	@Query(value = "select content from notification_master",nativeQuery = true)
List<String> findContent();

}
