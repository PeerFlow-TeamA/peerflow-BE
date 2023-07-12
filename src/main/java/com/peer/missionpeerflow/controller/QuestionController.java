package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.dto.request.QuestionModifyDTO;
import com.peer.missionpeerflow.exception.message.SuccessMessage;
import com.peer.missionpeerflow.service.QuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    public final QuestionService questionService;

    @PostMapping("/v1/question")
    public ResponseEntity<Object> create(@Valid @RequestBody QuestionCreateDTO questionCreateDTO) {
        questionService.creat(questionCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessMessage.of("Question created successfully"));
    }


    @PutMapping("/v1/question/{questionid}")
    public ResponseEntity<Object> create(@Valid @RequestBody QuestionModifyDTO questionModifyDTO, @PathVariable(name = "questionid") Long id) {
        questionService.modify(questionModifyDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessMessage.of("question modified successfully"));
    }
}
