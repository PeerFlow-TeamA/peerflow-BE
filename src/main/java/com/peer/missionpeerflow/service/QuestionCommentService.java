package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.mapper.QuestionCommentDTOMapper;
import com.peer.missionpeerflow.dto.request.QuestionCommentCreateDTO;
import com.peer.missionpeerflow.dto.request.QuestionCommentDeleteDTO;
import com.peer.missionpeerflow.dto.request.QuestionCommentModifyDTO;
import com.peer.missionpeerflow.dto.response.QuestionCommentDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.QuestionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionCommentService {

    private final QuestionCommentRepository questionCommentRepository;
    private final QuestionService questionService;
    private final UserRecordService userRecordService;

    public Page<QuestionCommentDTO> getQuestionCommandList(Long questionId, int page, int size){
        Question question = this.questionService.findQuestionByQuestionId(questionId);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<QuestionComment> questionCommentPage = this.questionCommentRepository.findByQuestionQuestionId(questionId, pageRequest);
        Page<QuestionCommentDTO> questionCommentDTOPage = QuestionCommentDTOMapper.INSTANCE.toDtoPage(questionCommentPage);
        return questionCommentDTOPage;
    }

    @Transactional
    public QuestionComment create(QuestionCommentCreateDTO questionCommentCreateDTO, Long questionId){
        Question question = this.questionService.findQuestionByQuestionId(questionId);
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

    @Transactional
    public void modify(QuestionCommentModifyDTO questionCommentModifyDTO, Long questionId, Long commentId){
        Question question = this.questionService.findQuestionByQuestionId(questionId);
        QuestionComment comment = this.findByCommentId(commentId);
        if (comment.getUserRecord().getPassword().equals(questionCommentModifyDTO.getPassword()) == false)
            throw new ForbiddenException("Password is incorrect");
        comment.setContent(questionCommentModifyDTO.getContent());
        comment.setUpdatedAt(LocalDateTime.parse(questionCommentModifyDTO.getUpdatedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss")));
    }

    @Transactional
    protected QuestionComment findByCommentId(Long commentId){
        Optional<QuestionComment> comment = this.questionCommentRepository.findById(commentId);
        if (comment.isPresent() == false)
            throw new NotFoundException("question comment not found");
        return comment.get();
    }

    @Transactional
    public void delete(QuestionCommentDeleteDTO questionCommentDeleteDTO, Long questionId, Long commentId){
        Question question = this.questionService.findQuestionByQuestionId(questionId);
        QuestionComment comment = this.findByCommentId(commentId);
        if(comment.getUserRecord().getPassword().equals(questionCommentDeleteDTO.getPassword()) == false)
            throw new ForbiddenException("Password is incorrect");
        this.questionCommentRepository.deleteById(commentId);
    }
}
