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

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/v1?category={category}&sort={sort}&page={page}&size={size}")
    public ResponseEntity<Object> getMainList(@Valid @PathVariable String category,
                                              @Valid @PathVariable String sort,
                                              @PathVariable int page,
                                              @PathVariable int size,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new QueryParameterException("Query parameter is invalid");
        }
        Page<MainQuestionDTO> questionDTOList = this.mainService.getMainList(category, sort, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(questionDTOList);
    }
}
