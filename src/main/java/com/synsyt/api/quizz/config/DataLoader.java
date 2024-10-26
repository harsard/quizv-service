package com.synsyt.api.quizz.config;

import com.synsyt.api.quizz.model.Answer;
import com.synsyt.api.quizz.model.Question;
import com.synsyt.api.quizz.model.Quiz;
import com.synsyt.api.quizz.model.User;
import com.synsyt.api.quizz.repository.QuestionRepository;
import com.synsyt.api.quizz.repository.UserRepository;
import com.synsyt.api.quizz.service.QuizService;
import com.synsyt.api.quizz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.synsyt.api.quizz.config.SecurityConfig.USER;

@Slf4j
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            UserService userService,
            QuizService quizService,
            QuestionRepository questionRepository,
            PasswordEncoder passwordEncoder) {
        return args -> loadTriviaData(userService, passwordEncoder, quizService, questionRepository);
    }

    @Transactional
    public void loadTriviaData(UserService userService, PasswordEncoder passwordEncoder, QuizService quizService, QuestionRepository questionRepository) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("Kandy@123"));
        user.setEmail("user@user.com");
        user.setRole(USER);
        user.setEnabled(true);
        user = userService.createUser(user);
        log.info("Created user: {}", user);

        Quiz quiz = new Quiz();
        quiz.setUser(user);
        quiz.setTitle("Trivia");
        quiz.setDescription("Trivia Question for fun");
        quiz = quizService.createQuiz(quiz);

        List<Trivia> triviaData = getTriviaData();
        for (Trivia trivia : triviaData) {
            Question question = new Question();
            question.setPrompt(trivia.question());

            List<Answer> answers = new ArrayList<>();
            for (Answer answer : trivia.answer()) {
                answer.setQuestion(question); // Link answer to the question
                answers.add(answer);
            }
            question.setAnswers(answers);
            question.setQuiz(quiz);
            questionRepository.save(question);
        }

    }
    private List<Trivia> getTriviaData() {
        List<Trivia> triviaList = Arrays.asList(
                new Trivia("A) What is the longest-running animated TV show in the United States?",
                        Arrays.asList(
                                new Answer("1.Family Guy", false),
                                new Answer("2.The Flintstones", false),
                                new Answer("3.The Simpsons", true),
                                new Answer("4.South Park", false)
                        )),
                new Trivia("B) Which musician is known for hit songs like \"Thriller\" and \"Beat It\"?",
                        Arrays.asList(
                                new Answer("1.Prince", false),
                                new Answer("2.Michael Jackson", true),
                                new Answer("3.Stevie Wonder", false),
                                new Answer("4.Lionel Richie", false)
                        )),
                new Trivia("C) What planet is Superman from?",
                        Arrays.asList(
                                new Answer("1.Krypton", true),
                                new Answer("2.Mars", false),
                                new Answer("3.Jupiter", false),
                                new Answer("4.Saturn", false)
                        )),
                new Trivia("D) In which city is the Statue of Liberty located?",
                        Arrays.asList(
                                new Answer("1.Boston", false),
                                new Answer("2.Washington, D.C.", false),
                                new Answer("3.New York City", true),
                                new Answer("4.hiladelphia", false)
                        )),
                new Trivia("E) Who wrote the Harry Potter series?",
                        Arrays.asList(
                                new Answer("1.R.R. Tolkien", false),
                                new Answer("2.J.K. Rowling", true),
                                new Answer("3.Suzanne Collins", false),
                                new Answer("4.George R.R. Martin", false)
                        ))
        );
        return triviaList;
    }

    private record Trivia(String question, List<Answer> answer) {}
}
