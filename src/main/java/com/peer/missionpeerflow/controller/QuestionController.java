package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.dto.request.QuestionDeleteDTO;
import com.peer.missionpeerflow.dto.request.QuestionModifyDTO;
import com.peer.missionpeerflow.dto.response.QuestionDetailDTO;
import com.peer.missionpeerflow.exception.message.SuccessMessage;
import com.peer.missionpeerflow.service.QuestionService;

import lombok.RequiredArgsConstructor;
import org.hibernate.QueryParameterException;
import org.hibernate.hql.internal.QueryExecutionRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    public final QuestionService questionService;

    @PostMapping("/v1/question")
    public ResponseEntity<Object> create(@Valid @RequestBody QuestionCreateDTO questionCreateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new QueryParameterException("Query parameter is invalid");
        questionService.creat(questionCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessMessage.of("Question created successfully"));
    }

    @GetMapping("/v1/question/{questionId}")
    public ResponseEntity<Object> detail(Model model, @PathVariable(name = "questionId") Long id) {
        QuestionDetailDTO questionDetailDTO = this.questionService.getQuestionDetail(id);
        model.addAttribute("questionDetail", questionDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body(questionDetailDTO);
    }

    @PutMapping("/v1/question/{questionid}")
    public ResponseEntity<Object> modify(@Valid @RequestBody QuestionModifyDTO questionModifyDTO, @PathVariable(name = "questionid") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new QueryParameterException("Query parameter is invalid");
        questionService.modify(questionModifyDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessMessage.of("question modified successfully"));
    }

    @PostMapping("/v1/question/{questionid}")
    public ResponseEntity<Object> delete(@Valid @RequestBody QuestionDeleteDTO questionDeleteDTO, @PathVariable(name = "questionid") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new QueryParameterException("Query parameter is invalid");
        questionService.delete(questionDeleteDTO, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(SuccessMessage.of("question deleted successfully"));
    }

    @PostMapping("/v1/question/{questionId}/recommendation")
    public ResponseEntity<Object> recommend(@PathVariable(name = "questionId") Long questionId){
        this.questionService.recommend(questionId);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessMessage.of("question recommendation success"));
    }
}