package com.gaurav.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gaurav.springboot.dao.QuestionDao;
import com.gaurav.springboot.question.Question;

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

	
	
	
	
}
