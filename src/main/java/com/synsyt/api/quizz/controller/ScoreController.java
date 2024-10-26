package com.synsyt.api.quizz.controller;

import com.synsyt.api.quizz.model.Score;
import com.synsyt.api.quizz.repository.ScoreRepository;
import com.synsyt.api.quizz.security.UserDetailsImpl;
import com.synsyt.api.quizz.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreRepository scoreRepository;

    @PostMapping
    public Score saveScore(@AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestBody Score score) {
        log.info("Saving score: {}", score);
        score.setUserId(userDetails.getId());
        Score savedScore = scoreService.saveScore(score);
        log.info("Saved score: {}", scoreRepository.findAll());
        return savedScore;
    }
}
