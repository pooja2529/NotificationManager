package com.notification.repository;

import org.springframework.data.repository.CrudRepository;

import com.notification.model.SmsTracker;

public interface SmsTrackerRepository extends CrudRepository<SmsTracker, Integer> {

}
