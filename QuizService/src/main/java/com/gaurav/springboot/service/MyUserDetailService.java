package com.gaurav.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.gaurav.springboot.quiz.User;
import com.gaurav.springboot.quiz.UserDto;
import com.gaurav.springboot.quiz.UserPrinciple;
import com.gaurav.springboot.dao.UserDetailRepo;

@Service
public class MyUserDetailService  implements UserDetailsService{

	@Autowired
	UserDetailRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repo.findUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		} else {
			return new UserPrinciple(user);
		}

	}

	}

//}
