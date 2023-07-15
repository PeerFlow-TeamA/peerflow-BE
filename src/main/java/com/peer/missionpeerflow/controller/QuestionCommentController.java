package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.questioncomment.QuestionCommentCreateDTO;
import com.peer.missionpeerflow.dto.request.questioncomment.QuestionCommentDeleteDTO;
import com.peer.missionpeerflow.dto.request.questioncomment.QuestionCommentModifyDTO;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.exception.message.SuccessMessage;
import com.peer.missionpeerflow.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class QuestionCommentController {
    private final QuestionCommentService questionCommentService;

    @PostMapping("v1/question/{questionId}/comment")
    public ResponseEntity<Object> create(@Valid @RequestBody QuestionCommentCreateDTO questionCommentCreateDTO, @PathVariable(name = "questionId") Long questionId){
        QuestionComment questionComment = this.questionCommentService.create(questionCommentCreateDTO, questionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("comment on question created successfully"));
    }

    @PutMapping("v1/question/{questionId}/comment/{commentId}")
    public ResponseEntity<Object> modify(@Valid @RequestBody QuestionCommentModifyDTO questionCommentModifyDTO, @PathVariable(name = "questionId") Long questionId, @PathVariable(name = "commentId") Long commentId){
        this.questionCommentService.modify(questionCommentModifyDTO, questionId, commentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("comment on question modified successfully"));
    }

    @PostMapping("v1/question/{questionId}/comment/{commentId}")
    public ResponseEntity<Object> delete(@Valid @RequestBody QuestionCommentDeleteDTO questionCommentDeleteDTO, @PathVariable(name = "questionId") Long questionId, @PathVariable(name = "commentId") Long commentId){
        this.questionCommentService.delete(questionCommentDeleteDTO, questionId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("comment on question deleted successfully");
    }
}
