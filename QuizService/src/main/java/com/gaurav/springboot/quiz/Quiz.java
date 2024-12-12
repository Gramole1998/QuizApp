package com.gaurav.springboot.quiz;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
//@Table(name="quiz_seq")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private List<Integer> questionIds;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		
		
		this.title = title;
	}
	public List<Integer> getQuestion() {
		return questionIds;
	}
	public void setQuestion(List<Integer> question) {
		this.questionIds = question;
	}
}
