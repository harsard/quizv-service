package com.synsyt.api.quizz.model;

import com.synsyt.api.quizz.dto.AnswerDto;
import com.synsyt.api.quizz.dto.QuestionDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String prompt;

    @Enumerated(EnumType.STRING)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();

    public static Question fromDto(QuestionDto questionDto) {
        Question question = new Question();
        question.setId(questionDto.getId());
        question.setPrompt(questionDto.getPrompt());
        for(AnswerDto answerDto : questionDto.getAnswers()) {
            question.getAnswers().add(Answer.fromDto(answerDto));
        }
        return question;
    }
}
