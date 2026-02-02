package com.telusko.questionservice.service;

import com.telusko.questionservice.dao.QuestionDao;
import com.telusko.questionservice.model.Question;
import com.telusko.questionservice.model.QuestionWrapper;
import com.telusko.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionDao.findAll());
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        return ResponseEntity.ok(questionDao.findByCategory(category));
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }

    // Generate question IDs for quiz
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        return ResponseEntity.ok(
                questionDao.findRandomQuestionsByCategory(categoryName, numQuestions)
        );
    }

    // Fetch questions by IDs (OPTIMIZED)
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {

        List<QuestionWrapper> wrappers = questionDao.findAllById(questionIds)
                .stream()
                .map(q -> new QuestionWrapper(
                        q.getId(),
                        q.getQuestionTitle(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(wrappers);
    }

    // Score calculation
    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int score = 0;

        for (Response response : responses) {
            Question question = questionDao.findById(response.getId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            if (response.getResponse().equals(question.getRightAnswer())) {
                score++;
            }
        }
        return ResponseEntity.ok(score);
    }
}
