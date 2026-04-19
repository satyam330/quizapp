package com.telusko.quizapp.dao;

import com.telusko.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz,Integer> {


}
