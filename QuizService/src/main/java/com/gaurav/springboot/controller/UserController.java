package com.gaurav.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaurav.springboot.quiz.UserDto;
import com.gaurav.springboot.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;

	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody UserDto userDto){
		return service.create(userDto);
		
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto userdto){
		return service.verify(userdto);
	}
}
