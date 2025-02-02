package com.gaurav.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gaurav.springboot.dao.UserDetailRepo;
import com.gaurav.springboot.quiz.User;
import com.gaurav.springboot.quiz.UserDto;

@Service
public class UserService {

	@Autowired
	UserDetailRepo repo;
	
	@Autowired
	AuthenticationManager authmanager;
	
	@Autowired
	JWTService jwtService;

	public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public ResponseEntity<String> create(UserDto userDto) {
		User user = dtoTOEntity(userDto);
		String hashPassword=encoder.encode(user.getPassword());
//		System.out.println(hashPassword);
		user.setPassword(hashPassword);
		repo.save(user);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);

	}

	public UserDto entityToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setPassword(user.getPassword());
		userDto.setUserName(user.getUserName());
		return userDto;

	}

	public User dtoTOEntity(UserDto userDto) {
		User user = new User();
		user.setPassword(userDto.getPassword());
		user.setUserName(userDto.getUserName());
		return user;
	}

	public ResponseEntity<String> verify(UserDto userdto) {
		// TODO Auto-generated method stub
		User user =dtoTOEntity(userdto);
//		String rawpassString=user.getPassword();
//		System.out.println(rawpassString);
		Authentication authentication =authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		System.out.println(authentication.isAuthenticated());
		if(authentication.isAuthenticated()) {
//			System.out.println(authentication.getName());
			return jwtService.generateToken(user);
		}
		return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
	}

}
