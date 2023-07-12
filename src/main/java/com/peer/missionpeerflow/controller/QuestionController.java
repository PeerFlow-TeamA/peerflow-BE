package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.dto.request.QuestionModifyDTO;
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
        HashMap<String, String> success = new HashMap<>();
        success.put("message", "Success to create a question");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }


    @PutMapping("/v1/question/{questionid}")
    public ResponseEntity<Object> create(@Valid @RequestBody QuestionModifyDTO questionModifyDTO, @PathVariable(name = "questionid") Long id) {
        questionService.modify(questionModifyDTO, id);
        HashMap<String, String> success = new HashMap<>();
        success.put("message", "Success to modify a question");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
