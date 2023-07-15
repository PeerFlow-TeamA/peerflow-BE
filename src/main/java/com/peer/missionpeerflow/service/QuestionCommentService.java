package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.QuestionCommentCreateDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.repository.QuestionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class QuestionCommentService {

    private final QuestionCommentRepository questionCommentRepository;
    private final QuestionService questionService;
    private final UserRecordService userRecordService;

    @Transactional
    public QuestionComment create(QuestionCommentCreateDTO questionCommentCreateDTO, Long questionId){
        Question question = questionService.findQuestionByQuestionId(questionId);
        UserRecord userRecord = this.userRecordService.create(questionCommentCreateDTO.getNickname(), questionCommentCreateDTO.getPassword());
        QuestionComment questionComment = QuestionComment.builder()
                .userRecord(userRecord)
                .question(question)
                .content(questionCommentCreateDTO.getContent())
                .createdAt(LocalDateTime.parse(questionCommentCreateDTO.getCreatedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss")))
                .build();
        this.questionCommentRepository.save(questionComment);
        return questionComment;
    }
}
