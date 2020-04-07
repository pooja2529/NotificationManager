package com.notification.repository;

import org.springframework.data.repository.CrudRepository;

import com.notification.model.EmailTracker;

public interface EmailTrackerRepository extends CrudRepository<EmailTracker, Integer>{

}
