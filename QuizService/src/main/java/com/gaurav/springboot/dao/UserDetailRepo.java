package com.gaurav.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gaurav.springboot.quiz.User;

@Repository
public interface UserDetailRepo extends JpaRepository<User, Integer> {

	public User findUserByUserName(String userName);

//	public Boolean existsByUserName(String name);
}
