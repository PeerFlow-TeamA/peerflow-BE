package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.mapper.RequestUserRecordDTOMapper;
import com.peer.missionpeerflow.dto.request.AnswerCommentCreateDTO;
import com.peer.missionpeerflow.dto.request.UserRecordDTO;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.repository.AnswerCommnetRepository;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.AnswerComment;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerCommentService {
    private final AnswerCommnetRepository answerCommnetRepository;
    private final AnswerService answerService;
    private final UserRecordService userRecordService;
    private final RequestUserRecordDTOMapper requestUserRecordDTOMapper;


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
}
