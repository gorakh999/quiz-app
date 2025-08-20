package com.example.quiz.dto;

import lombok.Data;

@Data
public class UserAnswer {
    private Long questionId;
    private String answer;
}
