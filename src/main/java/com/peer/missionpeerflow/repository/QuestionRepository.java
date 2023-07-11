package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
