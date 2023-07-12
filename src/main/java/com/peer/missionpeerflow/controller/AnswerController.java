package com.peer.missionpeerflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peer.missionpeerflow.dto.request.AnswerCreateDTO;
import com.peer.missionpeerflow.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

import java.util.HashMap;


@Controller
@RequestMapping("/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody AnswerCreateDTO answerCreateDTO) {
        answerService.create(answerCreateDTO);
        HashMap<String, String> success = new HashMap<>();
        success.put("message", "Success to create a answer");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
