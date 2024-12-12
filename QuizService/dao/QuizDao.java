package com.gaurav.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaurav.springboot.question.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
