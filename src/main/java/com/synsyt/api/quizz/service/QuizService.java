package com.synsyt.api.quizz.service;

import com.synsyt.api.quizz.dto.AnswerDto;
import com.synsyt.api.quizz.dto.QuestionDto;
import com.synsyt.api.quizz.dto.QuizDto;
import com.synsyt.api.quizz.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    List<Quiz> getAllQuizOfUser(Long userId);
    Quiz updateQuiz(Long id, QuizDto quizDto);
    Optional<Quiz> getQuizById(Long id);
    void deleteQuiz(Quiz quiz);
    Quiz addQuestionToQuiz(Long id, QuestionDto questionDto);
    Quiz addAnswerToQuestion(Long id, Long questionId, AnswerDto answerDto);
    Quiz updateQuestion(Long id, Long questionId, QuestionDto questionDto);
    void deleteQuestion(Quiz quiz, Long questionId);
}
