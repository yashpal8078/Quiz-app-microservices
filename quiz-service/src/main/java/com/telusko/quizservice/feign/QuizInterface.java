package com.telusko.quizservice.feign;

import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generate")
    ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String categoryName,
            @RequestParam Integer numQuestions
    );

    @PostMapping("question/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(
            @RequestBody List<Integer> questionIds
    );

    @PostMapping("question/getScore")
    ResponseEntity<Integer> getScore(
            @RequestBody List<Response> responses
    );
}
