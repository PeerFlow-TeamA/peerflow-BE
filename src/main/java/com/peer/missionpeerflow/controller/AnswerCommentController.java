package com.peer.missionpeerflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.peer.missionpeerflow.dto.request.AnswerCommentCreateDTO;
import com.peer.missionpeerflow.dto.request.AnswerCommentDeleteDTO;
import com.peer.missionpeerflow.dto.request.AnswerCommentModifyDTO;
import com.peer.missionpeerflow.exception.message.SuccessMessage;
import com.peer.missionpeerflow.exception.message.ErrorMessage;
import com.peer.missionpeerflow.service.AnswerCommentService;


@Controller
@RequiredArgsConstructor
public class AnswerCommentController {
    private final AnswerCommentService answerCommentService;

    @PostMapping("/v1/answer/{answerId}/comment")
    public ResponseEntity create(@RequestBody AnswerCommentCreateDTO answerCommentCreateDTO,
                                 @PathVariable(value="answerId") Long answerId,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of("Error occurred while Request Body Validation"));
        }
        answerCommentService.create(answerCommentCreateDTO, answerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("Comment on answer created successfully"));
    }

    @PutMapping("/v1/answer/{answerId}/comment/{answerCommentId}")
    public ResponseEntity modify(@RequestBody AnswerCommentModifyDTO answerCommentModifyDTO,
                       @PathVariable(value="answerId") Long answerId,
                       @PathVariable(value="answerCommentId") Long answerCommentId,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of("Error occurred while Request Body Validation"));
        }
        answerCommentService.modify(answerCommentModifyDTO, answerId, answerCommentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("Comment on answer modified successfully"));
    }

    @PostMapping("/v1/answer/{answerId}/comment/{answerCommentId}")
    public ResponseEntity delete(@RequestBody AnswerCommentDeleteDTO answerCommentDeleteDTO,
                        @PathVariable(value="answerId") Long answerId,
                        @PathVariable(value="answerCommentId") Long answerCommentId,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of("Error occurred while Request Body Validation"));
        }
        answerCommentService.delete(answerCommentDeleteDTO, answerId, answerCommentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("Comment on answer deleted successfully"));
    }
}
