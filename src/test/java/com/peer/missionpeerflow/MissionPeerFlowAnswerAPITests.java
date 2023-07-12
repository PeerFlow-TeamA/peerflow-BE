package com.peer.missionpeerflow;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MissionPeerFlowAnswerAPITests {

    @Autowired
    private MockMvc mockMvc;

    private RequestBodyJsonGenerator requestBodyJsonGenerator = new RequestBodyJsonGenerator();

    @Test
    void C_DET_01_service_green_00() throws Exception {
//        Question question = new Question();
//        question.setTitle("postman so hard");
//        question.setCreatedAt(LocalDateTime.now());
//        question.setNickname("jun");
//        question.setPassword("1234");
//        question.setCategory(Category.minishell);
//        question.setContent("postman so hard");
//        question.setView(0L);
//        question.setRecommend(0L);
//        questionRepository.save(question);
//
//        Long questionId = question.getQuestionId();
//        AnswerCreateDTO answerRequest1 = new AnswerCreateDTO(questionId, "jun", "1234", "postman so hard", "2021-06-01/00:00:00");
//
//        ResponseEntity expected = ResponseEntity.status(HttpStatus.CREATED).body("Answer created successfully");
//        when(answerController.create(answerRequest1)).thenReturn(expected);
//
//        assertTrue(answerController.create(answerRequest1).equals(expected));
        String question_requestBody = requestBodyJsonGenerator
                .addAttribute("title", "postman so hard")
                .addAttribute("nickname", "jun")
                .addAttribute("password", "1234")
                .addAttribute("category", "minishell")
                .addAttribute("content", "postman so hard")
                .addAttribute("createdAt", "2021-06-01/00:00:00")
                .toJson();
        MvcResult result = mockMvc.perform(
                            MockMvcRequestBuilders.post("/v1/question")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(question_requestBody)
                        ).andReturn();

        assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());


        String answer_requestBody = requestBodyJsonGenerator
                .addAttribute("questionId", "1")
                .addAttribute("nickname", "jun")
                .addAttribute("password", "1234")
                .addAttribute("content", "postman so hard")
                .addAttribute("createdAt", "2021-06-01/00:00:00")
                .toJson();
        MvcResult resultAnswer = mockMvc.perform(
                        MockMvcRequestBuilders.post("/v1/answer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(answer_requestBody)
                        ).andReturn();

        assertTrue(resultAnswer.getResponse().getStatus() == 201);
        assertTrue(resultAnswer.getResponse().getContentAsString().equals("{\"message\":\"Answer created successfully\"}"));
    }

    @Test
    void C_DET_01_service_RED_00() {
//        AnswerCreateDTO answerRequest1 = new AnswerCreateDTO(0L, "jun", "1234", "postman so hard", "2021-06-01/00:00:00");
//
//        ResponseEntity expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
//        when(answerController.create(answerRequest1, ).thenReturn(expected);
//
//        assertTrue(answerController.create(answerRequest1).equals(expected));
    }
}
