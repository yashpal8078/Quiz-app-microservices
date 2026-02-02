package com.telusko.quizservice.controller;

import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.QuizDto;
import com.telusko.quizservice.model.Response;
import com.telusko.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    // Create Quiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(
                quizDto.getCategoryName(),
                quizDto.getNumQuestions(),
                quizDto.getTitle()
        );
    }

    // Join Quiz using PIN
    @GetMapping("/pin/{quizCode}")
    public ResponseEntity<?> getQuizByPin(@PathVariable String quizCode) {
        Quiz quiz = quizService.getQuizByPin(quizCode);

        return ResponseEntity.ok(Map.of(
                "quizId", quiz.getId(),
                "title", quiz.getTitle(),
                "totalQuestions", quiz.getTotalQuestions()
        ));
    }

    // Get question one-by-one
    @GetMapping("/{quizId}/question/{index}")
    public ResponseEntity<QuestionWrapper> getQuestionByIndex(
            @PathVariable Integer quizId,
            @PathVariable int index) {
        return quizService.getQuestionByIndex(quizId, index);
    }

    // Submit full quiz
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(
            @PathVariable Integer id,
            @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
