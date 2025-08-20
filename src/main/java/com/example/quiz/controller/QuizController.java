package com.example.quiz.controller;

import com.example.quiz.dto.QuizForm;
import com.example.quiz.dto.UserAnswer;
import com.example.quiz.model.Question;
import com.example.quiz.model.Result;
import com.example.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/quiz")
    public String startQuiz(@RequestParam(name = "count", defaultValue = "3") int count,
                            @RequestParam(name = "seconds", defaultValue = "60") int seconds,
                            @RequestParam(name = "username", defaultValue = "Guest") String username,
                            Model model) {
        List<Question> questions = quizService.getRandomQuestions(count);
        List<UserAnswer> answers = new ArrayList<>();
        for (Question q : questions) {
            UserAnswer ua = new UserAnswer();
            ua.setQuestionId(q.getId());
            answers.add(ua);
        }
        QuizForm form = new QuizForm();
        form.setUsername(username);
        form.setAnswers(answers);
        model.addAttribute("quizForm", form);
        model.addAttribute("questions", questions);
        model.addAttribute("seconds", seconds);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submitQuiz(@ModelAttribute QuizForm quizForm, Model model) {
        Result result = quizService.evaluateQuiz(quizForm);
        model.addAttribute("result", result);
        return "result";
    }
}
