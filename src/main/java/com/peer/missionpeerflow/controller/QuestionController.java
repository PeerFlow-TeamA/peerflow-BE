package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionCreateRequest;
import com.peer.missionpeerflow.service.QuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    public final QuestionService questionService;

    @PostMapping("/v1/question")
    public String create(@RequestBody QuestionCreateRequest questionCreateRequest){
        questionService.creat(questionCreateRequest);
        return "question created successfully";
    }
}
