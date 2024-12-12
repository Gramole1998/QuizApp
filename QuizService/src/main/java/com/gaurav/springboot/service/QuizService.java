
package com.gaurav.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.gaurav.springboot.dao.QuizDao;
import com.gaurav.springboot.feign.QuizInterface;
import com.gaurav.springboot.quiz.QuestionWrapper;
import com.gaurav.springboot.quiz.Quiz;
import com.gaurav.springboot.quiz.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizdao;

	@Autowired
	QuizInterface quizinterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Integer> list = quizinterface.generateQue(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestion(list);
		quizdao.save(quiz);

		return new ResponseEntity<String>("success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {

		Optional<Quiz> quiz = quizdao.findById(id);
		List<Integer> questionids = quiz.get().getQuestion();
		List<QuestionWrapper> questionForUser = quizinterface.getQuest(questionids).getBody();
		return new ResponseEntity<>(questionForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> sumbitQuiz(int id, List<Response> responses) {
//		Quiz quiz=quizdao.findById(id).get();
		int right = quizinterface.getScore(responses).getBody();
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}

}
