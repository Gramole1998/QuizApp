package com.gaurav.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gaurav.springboot.quiz.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
