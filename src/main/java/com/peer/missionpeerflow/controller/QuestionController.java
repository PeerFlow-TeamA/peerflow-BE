package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.service.QuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    public final QuestionService questionService;

    @PostMapping("/v1/question")
    public ResponseEntity<Object> create(@Valid @RequestBody QuestionCreateDTO questionCreateRequest) {
        questionService.creat(questionCreateRequest);
        HashMap<String, String> success = new HashMap<>();
        success.put("message", "Success to create a question");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
