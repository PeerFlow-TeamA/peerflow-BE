package com.peer.missionpeerflow.service;


import com.peer.missionpeerflow.dto.mapper.MainQuestionDTOMapper;
import com.peer.missionpeerflow.dto.mapper.QuestionDetailDTOMapper;
import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.dto.request.QuestionDeleteDTO;
import com.peer.missionpeerflow.dto.request.QuestionModifyDTO;
import com.peer.missionpeerflow.dto.response.QuestionDetailDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.repository.UserRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRecordRepository userRecordRepository;
    private final QuestionDetailDTOMapper questionDetailDTOMapper;

    public void creat(@NotNull QuestionCreateDTO questionCreateRequest){
        UserRecord userRecord = new UserRecord();
        userRecord.setPassword(questionCreateRequest.getPassword());
        userRecord.setNickname(questionCreateRequest.getNickname());

        this.userRecordRepository.save(userRecord);

        Question question = new Question();
        question.setTitle(questionCreateRequest.getTitle());
        question.setCreatedAt(LocalDateTime.now());
        question.setNickname(questionCreateRequest.getNickname());
        question.setPassword(questionCreateRequest.getPassword());
        question.setCategory(questionCreateRequest.getCategory());
        question.setContent((questionCreateRequest.getContent()));
        question.setView(0L);
        question.setRecommend(0L);
        question.setUserRecord(userRecord);

        this.questionRepository.save(question);
    }

    public void modify(@NotNull QuestionModifyDTO questionModifyDTO, Long questionid){
        Optional<Question> question = this.questionRepository.findById(questionid);
        if (question.isPresent() == false)
                throw new NotFoundException("Question not found");
        if (question.get().getUserRecord().getPassword().compareTo(questionModifyDTO.getPassword()) != 0)
                throw new ForbiddenException("Password is incorrect");

        question.get().setTitle(questionModifyDTO.getTitle());
        question.get().setContent(questionModifyDTO.getContent());
        question.get().setCategory(questionModifyDTO.getCategory());
        question.get().setUpdatedAt(LocalDateTime.now());

        this.questionRepository.save(question.get());
    }

    public void delete(@NotNull QuestionDeleteDTO questionDeleteDTO, Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent() == false)
                throw new NotFoundException("Question not found");
        if (question.get().getUserRecord().getPassword().compareTo(questionDeleteDTO.getPassword()) != 0)
                throw new ForbiddenException("Password is incorrect");

    public Question findQuestionByQuestionId(Long questionId) {
        Optional<Question> question = this.questionRepository.findById(questionId);
        if (question.isPresent() == false)
            throw new NotFoundException("Question not found");
        return question.get();
    }
}

