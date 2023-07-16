package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.mapper.AnswerCommentDTOMapper;
import com.peer.missionpeerflow.dto.mapper.QuestionCommentDTOMapper;
import com.peer.missionpeerflow.dto.mapper.RequestAnswerCommentDTOMapper;
import com.peer.missionpeerflow.dto.mapper.RequestUserRecordDTOMapper;
import com.peer.missionpeerflow.dto.request.AnswerCommentCreateDTO;
import com.peer.missionpeerflow.dto.request.AnswerCommentDeleteDTO;
import com.peer.missionpeerflow.dto.request.AnswerCommentModifyDTO;
import com.peer.missionpeerflow.dto.response.AnswerCommentDTO;
import com.peer.missionpeerflow.dto.response.QuestionCommentDTO;
import com.peer.missionpeerflow.entity.*;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.AnswerCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final RequestAnswerCommentDTOMapper requestAnswerCommentDTOMapper;

    @Transactional
    public void create(AnswerCommentCreateDTO answerCommentCreateDTO, Long answerId) {
        Answer foundAnswer = this.answerService.findAnswerByAnswerId(answerId);
        UserRecord savedUserRecord = this.userRecordService.create(requestUserRecordDTOMapper.extractDTO(answerCommentCreateDTO));
        AnswerComment saveEntity = this.requestAnswerCommentDTOMapper.toEntity(answerCommentCreateDTO, answerId);
        saveEntity.setWriter(savedUserRecord);
        this.answerCommnetRepository.save(saveEntity);
    }

    @Transactional
    public void modify(AnswerCommentModifyDTO answerCommentModifyDTO, Long answerId, Long answerCommentId) {
        Answer foundAnswer = this.answerService.findAnswerByAnswerId(answerId);
        AnswerComment foundComment = this.findByAnswerCommentId(answerCommentId);
        if (answerCommentModifyDTO.getPassword().equals(foundComment.getPassword()) == false)
            throw new NotFoundException("Password incorrect");
        AnswerComment oldAnswerComment = this.findByAnswerCommentId(answerCommentId);
        oldAnswerComment.setContent(answerCommentModifyDTO.getContent());
        this.answerCommnetRepository.save(oldAnswerComment);
    }

    @Transactional
    public void delete(AnswerCommentDeleteDTO answerCommentDeleteDTO, Long answerId, Long answerCommentId) {
        Answer foundAnswer = this.answerService.findAnswerByAnswerId(answerId);

        AnswerComment foundAnswerComment = foundAnswer.getAnswerCommentList().stream()
                .filter(answerComment -> answerComment.getAnswerCommentId().equals(answerCommentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Comment on answer not found"));

        if (answerCommentDeleteDTO.getPassword().equals(foundAnswerComment.getWriter().getPassword()) == false)
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

    @Transactional
    public Page<AnswerCommentDTO> getAnswerCommandList(Long answerId, int page, int size){
        Answer answer = this.answerService.findAnswerByAnswerId(answerId);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<AnswerComment> answerCommentPage = this.answerCommnetRepository.findByAnswerAnswerId(answerId, pageRequest);
        Page<AnswerCommentDTO> answerCommentDTOPage = AnswerCommentDTOMapper.INSTANCE.toDtoPage(answerCommentPage);
        return answerCommentDTOPage;
    }
}
