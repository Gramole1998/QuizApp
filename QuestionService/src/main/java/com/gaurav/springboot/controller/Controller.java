package com.gaurav.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaurav.springboot.question.Question;
import com.gaurav.springboot.question.QuestionWrapper;
import com.gaurav.springboot.question.Response;
import com.gaurav.springboot.service.QuestionService;

@RestController
@RequestMapping("/question")
public class Controller{
	@Autowired
	QuestionService service;

	@GetMapping("/category/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category) {
		return service.getAllQuestionsByCategory(category);
	}

	@GetMapping("/allquestion")
	public List<Question> getQuestion() {
		return service.getAllQuestions();
	}

	@PostMapping("/add")
	public String addQuestion(@RequestBody Question question) {
		service.addQuestion(question);
		return "success";
	}
	@PostMapping("/allAdd")
	public String addAllQuest(@RequestBody List<Question> quest) {
		service.addAllQuestion(quest);
		return "added all data";
		
	}
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> generateQue(@RequestParam String category,@RequestParam int numQ){
		
		return service.generateQue(category,numQ);
		
	}
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuest(@RequestBody List<Integer> questIds) {
		
		return service.getQuestion(questIds) ;
		
	}
	@PostMapping("getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
		
		return service.getScore(responses) ;
		
	}	

}
