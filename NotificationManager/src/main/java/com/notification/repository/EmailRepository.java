package com.notification.repository;

import org.springframework.data.repository.CrudRepository;

import com.notification.model.EmailMaster;

public interface EmailRepository extends CrudRepository<EmailMaster, Integer> {

}
