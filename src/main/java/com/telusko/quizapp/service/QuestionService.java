package com.telusko.quizapp.service;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to add quesiton !!!", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteById(Integer id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("deleted successfully !!!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("some thing went wrong !!!", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> updateQuestion(Integer id, Question question) {
        try {
            Question existingQuestion = questionDao.findById(id).orElse(null);

            if (existingQuestion == null) {
                return new ResponseEntity<>("Question not found",HttpStatus.BAD_REQUEST);
            }

            existingQuestion.setCategory(question.getCategory());
            existingQuestion.setQuestionTitle(question.getQuestionTitle());
            existingQuestion.setOption1(question.getOption1());
            existingQuestion.setOption2(question.getOption2());
            existingQuestion.setOption3(question.getOption3());
            existingQuestion.setRightAnswer(question.getRightAnswer());
            existingQuestion.setDifficultylevel(question.getDifficultylevel());

            questionDao.save(existingQuestion);

            return new ResponseEntity<>("updated Successfully !!!!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong  !!!!", HttpStatus.BAD_REQUEST);

    }
}