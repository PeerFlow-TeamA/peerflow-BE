package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.response.MainQuestionDTO;
import com.peer.missionpeerflow.service.MainService;
import lombok.RequiredArgsConstructor;
import org.hibernate.QueryParameterException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/v1")
    public ResponseEntity<Object> getMainList(@Valid @RequestParam String category,
                                              @Valid @RequestParam String sort,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        Page<MainQuestionDTO> questionDTOList = this.mainService.getMainList(category, sort, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(questionDTOList);
    }

    @GetMapping("/v1/search")
    public ResponseEntity<Object> getSearchList(@Valid @RequestParam String title,
                                                @Valid @RequestParam String sort,
                                                @RequestParam int page,
                                                @RequestParam int size) {
        Page<MainQuestionDTO> questionDTOList = this.mainService.getSearchList(title, sort, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(questionDTOList);
    }
}
