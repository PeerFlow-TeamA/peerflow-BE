package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionCommentCreateDTO;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.exception.message.SuccessMessage;
import com.peer.missionpeerflow.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class QuestionCommentController {
    private final QuestionCommentService questionCommentService;

    @PostMapping("v1/question/{questionid}/comment")
    public ResponseEntity<Object> create(@Valid @RequestBody QuestionCommentCreateDTO questionCommentCreateDTO, @PathVariable(name = "questionid") Long questionId){
        QuestionComment questionComment = this.questionCommentService.create(questionCommentCreateDTO, questionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("comment on question created successfully"));
    }
}
