package com.gaurav.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gaurav.springboot.dao.QuestionDao;
import com.gaurav.springboot.question.Question;
import com.gaurav.springboot.question.QuestionWrapper;
import com.gaurav.springboot.question.Response;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao dao;
	
	
	public void addQuestion(Question question) {
		dao.save(question);
	}
	public void addAllQuestion(List<Question> list) {
		dao.saveAll(list);
	}

	public List<Question> getAllQuestionsByCategory(String category) {
		// TODO Auto-generated method stub
		return dao.findByCategory(category);
	}

	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	public ResponseEntity<List<Integer>> generateQue(String category, int numQ) {
		// TODO Auto-generated method stub
		List<Integer> questionsIds=new ArrayList<>();
		List<Question> questions=dao.findRandomQuestionByCategory(category, numQ);
		for (Question question : questions) {
			questionsIds.add(question.getId());
		}
		return new ResponseEntity<List<Integer>>(questionsIds,HttpStatus.OK);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuestion(List<Integer> questIds) {
		// TODO Auto-generated method stub
		List<Question> questList=dao.findAllById(questIds);
		List<QuestionWrapper> wrappers=new ArrayList<>();
		
		for (Question question : questList) {
			QuestionWrapper wrapper =new QuestionWrapper(question.getId(),question.getQuestion(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		// TODO Auto-generated method stub
		int right=0;
		for (Response response : responses) {
			Question question=dao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getCorrectAns())) {
				right++;
			}
		}
		
		return new ResponseEntity<Integer>(right,HttpStatus.OK);
	}

	
	
	
	
}
