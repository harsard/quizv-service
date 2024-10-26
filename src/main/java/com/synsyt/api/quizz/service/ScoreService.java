package com.synsyt.api.quizz.service;

import com.synsyt.api.quizz.model.Score;
import com.synsyt.api.quizz.repository.ScoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public Score saveScore(Score score) {
        Score scoreEntity = null;
        if(scoreRepository.findByUuid(score.getUuid().toString()).isEmpty()){
             scoreEntity = new Score();
            scoreEntity.setUserId(score.getUserId());
            scoreEntity.setScore(score.getScore());
            if(score.getCreatedTime() != null){
                log.info("score.getCreatedTime() :{}", score.getCreatedTime());
                scoreEntity.setCreatedTime(score.getCreatedTime());
            }else{
                scoreEntity.setCreatedTime(LocalDateTime.now());
            }
            scoreEntity.setUuid(score.getUuid());
            scoreEntity = scoreRepository.save(scoreEntity);
        }else{
            log.info("Score already exists:{}", score.getUuid());
        }
        return scoreEntity;
    }
}
