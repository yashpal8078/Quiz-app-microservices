package com.telusko.quizservice.dao;

import com.telusko.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
    Optional<Quiz> findByQuizCode(String quizCode);
}
