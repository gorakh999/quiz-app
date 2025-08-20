package com.example.quiz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int score;
    private int totalQuestions;
    private Date dateSubmitted;
    private long timeTaken; // seconds
}
