package com.example.quiz.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuizForm {
    private String username;
    private long timeTaken; // seconds (client-reported)
    private List<UserAnswer> answers;
}
