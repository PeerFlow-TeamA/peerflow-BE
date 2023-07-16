package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.Question;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Override
    Optional<Question> findById(Long id);

    @Override
    void deleteById(Long id);
}
