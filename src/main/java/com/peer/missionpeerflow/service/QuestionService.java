package com.peer.missionpeerflow.service;


import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.repository.UserRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRecordRepository userRecordRepository;

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
}

