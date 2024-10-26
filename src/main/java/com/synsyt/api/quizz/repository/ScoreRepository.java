package com.synsyt.api.quizz.repository;

import com.synsyt.api.quizz.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    Optional<Score> findByUuid(String uuid);
}
