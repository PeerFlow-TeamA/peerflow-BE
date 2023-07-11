package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.response.MainQuestionDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.peer.missionpeerflow.exception.NotFoundException;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/v1?category={category}&sort={sort}&page={page}&size={size}")
    public ResponseEntity<Page<MainQuestionDTO>> getMainList(Model model,
                                                            @PathVariable String category,
                                                            @PathVariable String sort,
                                                            @PathVariable int page,
                                                            @PathVariable int size) {
        try {
            Page<MainQuestionDTO> questionDTOList = this.mainService.getMainList(category, sort, page, size);
            model.addAttribute("questionList", questionDTOList);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(questionDTOList);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
