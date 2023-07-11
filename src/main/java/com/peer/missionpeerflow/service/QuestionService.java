package com.peer.missionpeerflow.service;


import com.peer.missionpeerflow.dto.request.QuestionCreateRequest;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void creat(@NotNull QuestionCreateRequest questionCreateRequest){
        Question question = new Question();
        question.setTitle(questionCreateRequest.getTitle());
        question.setCreatedAt(questionCreateRequest.getCreatedAt());
        question.setNickname(questionCreateRequest.getNickname());
        question.setPassword(questionCreateRequest.getPassword());
        question.setCategory(questionCreateRequest.getCategory());
        question.setContent((questionCreateRequest.getContent()));
        question.setView(0L);
        this.questionRepository.save(question);
    }
}

