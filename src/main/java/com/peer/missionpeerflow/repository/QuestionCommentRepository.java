package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.QuestionComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
    Page<QuestionComment> findByQuestionQuestionId(Long questionId, PageRequest pageRequest);
}
