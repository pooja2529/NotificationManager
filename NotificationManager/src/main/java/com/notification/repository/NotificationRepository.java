package com.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notification.model.NotificationMaster;

public interface NotificationRepository extends JpaRepository<NotificationMaster, Integer>{



}
