package com.telusko.quizservice.service;

import com.telusko.quizservice.dao.QuizDao;
import com.telusko.quizservice.feign.QuizInterface;
import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    // Create Quiz
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions =
                quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quiz.setTotalQuestions(questions.size());

        // Generate 4-digit PIN
        quiz.setQuizCode(String.valueOf((int)(Math.random() * 9000) + 1000));

        quizDao.save(quiz);

        return new ResponseEntity<>(
                "Quiz Created Successfully. PIN: " + quiz.getQuizCode(),
                HttpStatus.CREATED
        );

    }

    // Get Quiz by PIN
    public Quiz getQuizByPin(String quizCode) {
        return quizDao.findByQuizCode(quizCode)
                .orElseThrow(() -> new RuntimeException("Invalid Quiz PIN"));
    }

    // Get Question by Index
    public ResponseEntity<QuestionWrapper> getQuestionByIndex(Integer quizId, int index) {

        Quiz quiz = quizDao.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        if (quiz.getQuestionIds() == null || quiz.getQuestionIds().isEmpty()) {
            throw new RuntimeException("No questions found for this quiz");
        }

        if (index >= quiz.getQuestionIds().size()) {
            throw new RuntimeException("Quiz completed");
        }

        Integer questionId = quiz.getQuestionIds().get(index);

        QuestionWrapper question =
                quizInterface.getQuestionsFromId(List.of(questionId))
                        .getBody()
                        .get(0);

        return ResponseEntity.ok(question);
    }


    // Submit Quiz
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }
}
