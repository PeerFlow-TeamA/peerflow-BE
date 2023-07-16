package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.exception.message.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.peer.missionpeerflow.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import com.peer.missionpeerflow.dto.request.AnswerDeleteDTO;
import com.peer.missionpeerflow.dto.request.AnswerModifyDTO;
import com.peer.missionpeerflow.dto.request.AnswerCreateDTO;
import com.peer.missionpeerflow.exception.message.SuccessMessage;

@Controller
@RequestMapping("/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody AnswerCreateDTO answerCreateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of("Error occurred while Request Body Validation"));
        }
        answerService.create(answerCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("Answer created successfully"));
    }

    @PutMapping("/{answerId}")
    public ResponseEntity modify(@Valid @RequestBody AnswerModifyDTO answerModifyDTO, @PathVariable(name = "answerId") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of("Error occurred while Request Body Validation"));
        }
        answerService.modify(answerModifyDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("Answer modified successfully"));
    }

    @PostMapping("/{answerId}")
    public ResponseEntity delete(@Valid @RequestBody AnswerDeleteDTO answerDeleteDTO, @PathVariable(name = "answerId") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of("Error occurred while Request Body Validation"));
        }
        answerService.delete(answerDeleteDTO, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(SuccessMessage.of("Answer deleted successfully"));
    }

    @PostMapping("/{answerId}/recommendation")
    public ResponseEntity recommend(@PathVariable(name = "answerId") Long answerId){
        this.answerService.recommend(answerId);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessMessage.of("answer recommendation success"));
    }

    @PostMapping("/{answerId}/adopt")
    public ResponseEntity adopt(@PathVariable(name = "answerId") Long answerId){
        this.answerService.adopt(answerId);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessMessage.of("answer adopted successfully"));
    }
}
