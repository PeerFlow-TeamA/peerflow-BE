package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
}
