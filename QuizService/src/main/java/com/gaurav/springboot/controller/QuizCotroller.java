package com.gaurav.springboot.controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaurav.springboot.quiz.QuestionWrapper;
import com.gaurav.springboot.quiz.QuizDto;
import com.gaurav.springboot.quiz.Response;
import com.gaurav.springboot.quiz.UserDto;
import com.gaurav.springboot.service.MyUserDetailService;
import com.gaurav.springboot.service.QuizService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/quiz")
public class QuizCotroller {
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	MyUserDetailService userService;

	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto dto){
		
		return quizService.createQuiz(dto.getCategory(),dto.getNumQ(),dto.getTitle());
		
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
		
		return quizService.getQuiz(id);
		}
	@PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id,@RequestBody List<Response> responces){
		return quizService.sumbitQuiz(id,responces);
		
		}
	
	@GetMapping("/csrf-token")
	public CsrfToken getcsrf(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf") ;
	}
	
	
}
