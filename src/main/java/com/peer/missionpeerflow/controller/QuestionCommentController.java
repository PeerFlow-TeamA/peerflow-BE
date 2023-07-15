package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionCommentCreateDTO;
import com.peer.missionpeerflow.dto.request.QuestionCommentDeleteDTO;
import com.peer.missionpeerflow.dto.request.QuestionCommentModifyDTO;
import com.peer.missionpeerflow.dto.response.QuestionCommentDTO;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.exception.message.SuccessMessage;
import com.peer.missionpeerflow.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class QuestionCommentController {
    private final QuestionCommentService questionCommentService;

    @GetMapping("/v1/question/{questionId}/comment")
    public ResponseEntity<Object> getQuestionCommentList(@PathVariable Long questionId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<QuestionCommentDTO> questionCommentDTOPage = this.questionCommentService.getQuestionCommandList(questionId, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(questionCommentDTOPage);
    }

    @PostMapping("/v1/question/{questionId}/comment")
    public ResponseEntity<Object> create(@Valid @RequestBody QuestionCommentCreateDTO questionCommentCreateDTO, @PathVariable(name = "questionId") Long questionId){
        QuestionComment questionComment = this.questionCommentService.create(questionCommentCreateDTO, questionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("comment on question created successfully"));
    }

    @PutMapping("/v1/question/{questionId}/comment/{commentId}")
    public ResponseEntity<Object> modify(@Valid @RequestBody QuestionCommentModifyDTO questionCommentModifyDTO, @PathVariable(name = "questionId") Long questionId, @PathVariable(name = "commentId") Long commentId){
        this.questionCommentService.modify(questionCommentModifyDTO, questionId, commentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("comment on question modified successfully"));
    }

    @PostMapping("/v1/question/{questionId}/comment/{commentId}")
    public ResponseEntity<Object> delete(@Valid @RequestBody QuestionCommentDeleteDTO questionCommentDeleteDTO, @PathVariable(name = "questionId") Long questionId, @PathVariable(name = "commentId") Long commentId){
        this.questionCommentService.delete(questionCommentDeleteDTO, questionId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("comment on question deleted successfully");
    }
}
