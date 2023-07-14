package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.mapper.RequestUserRecordDTOMapper;
import com.peer.missionpeerflow.dto.request.AnswerCommentCreateDTO;
import com.peer.missionpeerflow.dto.request.AnswerCommentDeleteDTO;
import com.peer.missionpeerflow.dto.request.AnswerCommentModifyDTO;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.repository.AnswerCommentRepository;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.AnswerComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerCommentService {
    private final AnswerCommentRepository answerCommnetRepository;
    private final AnswerService answerService;
    private final UserRecordService userRecordService;
    private final RequestUserRecordDTOMapper requestUserRecordDTOMapper;

    @Transactional
    public void create(AnswerCommentCreateDTO answerCommentCreateDTO, Long answerId) {
        Answer foundAnswer = this.answerService.findAnswerByAnswerId(answerId);
        UserRecord savedUserRecord = this.userRecordService.create(requestUserRecordDTOMapper.extractDTO(answerCommentCreateDTO));
        AnswerComment saveEntity = AnswerComment.builder()
                .answer(foundAnswer)
                .writer(savedUserRecord)
                .content(answerCommentCreateDTO.getContent())
                .build();
        this.answerCommnetRepository.save(saveEntity);
    }

    @Transactional
    public void modify(AnswerCommentModifyDTO answerCommentModifyDTO, Long answerId, Long answerCommentId) {
        Answer foundAnswer = this.answerService.findAnswerByAnswerId(answerId);
        if (answerCommentModifyDTO.getPassword().equals(foundAnswer.getPassword()) == false)
            throw new NotFoundException("Password incorrect");
        AnswerComment oldAnswerComment = this.findByAnswerCommentId(answerCommentId);
        AnswerComment modifiedAnswerComment = AnswerComment.builder()
                .answer(foundAnswer)
                .writer(oldAnswerComment.getWriter())
                .content(answerCommentModifyDTO.getContent())
                .build();
        this.answerCommnetRepository.save(modifiedAnswerComment);
    }

    @Transactional
    public void delete(AnswerCommentDeleteDTO answerCommentDeleteDTO, Long answerId, Long answerCommentId) {
        Answer foundAnswer = this.answerService.findAnswerByAnswerId(answerId);
        AnswerComment foundAnswerComment = this.findByAnswerCommentId(answerCommentId);
        if (foundAnswerComment.getAnswer().getAnswerId().equals(foundAnswer.getAnswerId()) == false)
            throw new NotFoundException("Comment on answer not found");
        if (foundAnswerComment.getAnswer().getPassword().equals(answerCommentDeleteDTO.getPassword()) == false)
            throw new NotFoundException("Password incorrect");
        this.answerCommnetRepository.delete(foundAnswerComment);
    }

    @Transactional(readOnly = true)
    protected AnswerComment findByAnswerCommentId(Long answerCommentId) {
        Optional<AnswerComment> foundAnswerComment = this.answerCommnetRepository.findByAnswerCommentId(answerCommentId);
        if (foundAnswerComment.isEmpty())
            throw new NotFoundException("Comment on answer not found");
        return foundAnswerComment.get();
    }
}
