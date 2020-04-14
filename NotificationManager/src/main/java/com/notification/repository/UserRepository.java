package com.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.notification.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	@Query(value = "select email from user where mobilenumber IN (:mobilenumber)",nativeQuery = true)
	List<String> findAllEmails(@Param("mobilenumber")List<String> mobilenumber);

	@Query(value = "select mobilenumber from user where userid IN (:userid)",nativeQuery = true)
	List<String> findAllMob(@Param("userid")List<Integer> userid);

	@Query(value = "select deviceid from user where userid IN (:userid)",nativeQuery = true)
	List<String> findByUserIds(@Param("userid")List<Integer> userid);
	
	@Query(value = "select mobilenumber from user",nativeQuery = true)
	List<String> findMobile();

}
