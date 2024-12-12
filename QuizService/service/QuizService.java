
package com.gaurav.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gaurav.springboot.dao.QuestionDao;
import com.gaurav.springboot.dao.QuizDao;
import com.gaurav.springboot.question.Question;
import com.gaurav.springboot.question.QuestionWrapper;
import com.gaurav.springboot.question.Quiz;
import com.gaurav.springboot.question.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizdao;
	@Autowired
	QuestionDao queDao;
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> list=queDao.findRandomQuestionByCategory(category,numQ);
		
		Quiz quiz2 = new Quiz();
		quiz2.setTitle(title);
		quiz2.setQuestion(list);
		quizdao.save(quiz2);
		
		return new ResponseEntity<String>("success", HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		
		Optional<Quiz> quiz=quizdao.findById(id);
		List<Question> questions=quiz.get().getQuestion();
		List<QuestionWrapper> questionForUser=new ArrayList<>();
		for (Question q : questions ) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionForUser.add(qw);
		}
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}
	public ResponseEntity<Integer> sumbitQuiz(int id,List<Response> responses) {
		Quiz quiz=quizdao.findById(id).get();
		List<Question> questions=quiz.getQuestion();
		int right=0;
		int i=0;
		for (Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getCorrectAns())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<Integer>(right,HttpStatus.OK);
	}

	
}
