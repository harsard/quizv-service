package com.synsyt.api.quizz.model;

import com.synsyt.api.quizz.dto.AnswerDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public static Answer fromDto(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setId(answerDto.getId());
        answer.setText(answerDto.getText());
        answer.setCorrect(answerDto.isCorrect());
        return answer;
    }
}
