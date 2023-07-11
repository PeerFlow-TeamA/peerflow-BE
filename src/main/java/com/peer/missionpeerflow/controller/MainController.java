package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.response.MainQuestionDTO;
import com.peer.missionpeerflow.exception.errormessage.ErrorMessage;
import com.peer.missionpeerflow.service.MainService;
import lombok.RequiredArgsConstructor;
import org.hibernate.QueryParameterException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/v1?category={category}&sort={sort}&page={page}&size={size}")
    public ResponseEntity<Object> getMainList(Model model,
                                                            @PathVariable String category,
                                                            @PathVariable String sort,
                                                            @PathVariable int page,
                                                            @PathVariable int size) {
        try {
            if (category == null || category.isEmpty() || sort == null || category.isEmpty() || page < 0 || size < 0) {
                throw new QueryParameterException("Query Parameter Error : getMainList");
            }
            Page<MainQuestionDTO> questionDTOList = this.mainService.getMainList(category, sort, page, size);
            model.addAttribute("questionList", questionDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(questionDTOList);
        } catch (QueryParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(e));
        }
    }
}
