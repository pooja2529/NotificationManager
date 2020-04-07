package com.notification.repository;

import org.springframework.data.repository.CrudRepository;

import com.notification.model.SmsMaster;

public interface SmsRepository extends CrudRepository<SmsMaster, Integer>
{

}
