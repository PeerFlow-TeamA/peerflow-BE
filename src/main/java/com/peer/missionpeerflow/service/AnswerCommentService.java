package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.mapper.RequestUserRecordDTOMapper;
import com.peer.missionpeerflow.dto.request.answercomment.AnswerCommentCreateDTO;
import com.peer.missionpeerflow.dto.request.answercomment.AnswerCommentDeleteDTO;
import com.peer.missionpeerflow.dto.request.answercomment.AnswerCommentModifyDTO;
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
        foundAnswer.getAnswerCommentList().add(saveEntity);

        this.answerService.save(foundAnswer);
        this.answerCommnetRepository.save(saveEntity);
    }

    @Transactional
    public void modify(AnswerCommentModifyDTO answerCommentModifyDTO, Long answerId, Long answerCommentId) {
        Answer foundAnswer = this.answerService.findAnswerByAnswerId(answerId);
        if (answerCommentModifyDTO.getPassword().equals(foundAnswer.getPassword()) == false)
            throw new NotFoundException("Password incorrect");
        AnswerComment oldAnswerComment = this.findByAnswerCommentId(answerCommentId);
        oldAnswerComment.setContent(answerCommentModifyDTO.getContent());

        this.answerService.save(foundAnswer);
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

        foundAnswer.getAnswerCommentList().remove(foundAnswerComment);
        this.answerService.save(foundAnswer);
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
