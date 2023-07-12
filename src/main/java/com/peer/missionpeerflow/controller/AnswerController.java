package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.AnswerModifyDTO;
import com.peer.missionpeerflow.exception.message.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peer.missionpeerflow.dto.request.AnswerCreateDTO;
import com.peer.missionpeerflow.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody AnswerCreateDTO answerCreateDTO) {
        answerService.create(answerCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("Answer created successfully"));
    }

    @PutMapping("/{answerId}")
    public ResponseEntity modify(@Valid @RequestBody AnswerModifyDTO answerModifyDTO, @PathVariable(name = "answerId") Long id) {
        answerService.modify(answerModifyDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessMessage.of("Answer modified successfully"));
    }
}
