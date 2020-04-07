package com.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.notification.controller","com.notification.model","com.notification.service","com.notification.util"})
@EnableJpaRepositories(basePackages = {"com.notification.repository"})
public class NotificationManagerApplication {


    
	public static void main(String[] args) {
		SpringApplication.run(NotificationManagerApplication.class, args);
		
	}
	
	
}
