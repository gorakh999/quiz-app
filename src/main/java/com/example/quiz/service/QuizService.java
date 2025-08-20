package com.example.quiz.service;

import com.example.quiz.dto.QuizForm;
import com.example.quiz.dto.UserAnswer;
import com.example.quiz.model.Question;
import com.example.quiz.model.Result;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepo;
    private final ResultRepository resultRepo;

    public List<Question> getRandomQuestions(int count) {
        return questionRepo.findRandomQuestions(count);
    }

    public Result evaluateQuiz(QuizForm form) {
        int score = 0;
        Map<Long, String> correctMap = new HashMap<>();
        if (form.getAnswers() != null) {
            for (UserAnswer ans : form.getAnswers()) {
                Optional<Question> qOpt = questionRepo.findById(ans.getQuestionId());
                if (qOpt.isPresent()) {
                    Question q = qOpt.get();
                    correctMap.put(q.getId(), q.getCorrectAnswer());
                    if (q.getCorrectAnswer().equals(ans.getAnswer())) {
                        score++;
                    }
                }
            }
        }
        Result result = new Result();
        result.setUsername(form.getUsername() == null || form.getUsername().isEmpty() ? "Guest" : form.getUsername());
        result.setScore(score);
        result.setTotalQuestions(form.getAnswers() != null ? form.getAnswers().size() : 0);
        result.setDateSubmitted(new java.util.Date());
        result.setTimeTaken(form.getTimeTaken());
        resultRepo.save(result);
        return result;
    }
}
