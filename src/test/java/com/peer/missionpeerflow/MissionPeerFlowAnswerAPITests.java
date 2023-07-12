package com.peer.missionpeerflow;


import com.peer.missionpeerflow.dto.request.AnswerCreateDTO;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.entity.Question;

import com.peer.missionpeerflow.util.Category;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.peer.missionpeerflow.controller.AnswerController;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MissionPeerFlowAnswerAPITests {

    @Mock
    @Autowired
    private AnswerController answerController;

    @Mock
    @Autowired
    private QuestionRepository questionRepository;
    @Test
    void C_DET_01_service_green_00() {
        Question question = new Question();
        question.setTitle("postman so hard");
        question.setCreatedAt(LocalDateTime.now());
        question.setNickname("jun");
        question.setPassword("1234");
        question.setCategory(Category.minishell);
        question.setContent("postman so hard");
        question.setView(0L);
        question.setRecommend(0L);
        questionRepository.save(question);

        Long questionId = question.getQuestionId();
        AnswerCreateDTO answerRequest1 = new AnswerCreateDTO(questionId, "jun", "1234", "postman so hard", "2021-06-01T00:00:00");

        ResponseEntity expected = ResponseEntity.status(HttpStatus.CREATED).body("Answer created successfully");
        when(answerController.create(answerRequest1)).thenReturn(expected);

        assertTrue(answerController.create(answerRequest1).equals(expected));
    }

    @Test
    void C_DET_01_service_RED_00() {
        AnswerCreateDTO answerRequest1 = new AnswerCreateDTO(0L, "jun", "1234", "postman so hard", "2021-06-01T00:00:00");

        ResponseEntity expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        when(answerController.create(answerRequest1)).thenReturn(expected);

        assertTrue(answerController.create(answerRequest1).equals(expected));
    }
}
