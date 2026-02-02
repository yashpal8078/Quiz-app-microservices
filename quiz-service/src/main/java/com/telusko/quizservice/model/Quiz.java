package com.telusko.quizservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String title;

        @Column(unique = true)
        private String quizCode;   // PIN

        private int totalQuestions;

        @ElementCollection
        private List<Integer> questionIds;
    }
