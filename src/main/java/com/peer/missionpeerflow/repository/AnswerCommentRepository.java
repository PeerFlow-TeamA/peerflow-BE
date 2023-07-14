package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.AnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long>{
    Optional<AnswerComment> findByAnswerCommentId(Long answerCommentId);
}
