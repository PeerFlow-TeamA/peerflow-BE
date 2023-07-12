package com.peer.missionpeerflow;


import com.peer.missionpeerflow.utils.RequestBodyJSONGenerator;
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

    private RequestBodyJSONGenerator requestBodyJsonGenerator = new RequestBodyJSONGenerator();

    @Test
    void C_DET_01_service_green_00() throws Exception {

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

        assertTrue(resultAnswer.getResponse().getStatus() == HttpStatus.CREATED.value());
        assertTrue(resultAnswer.getResponse().getContentAsString().equals("{\"message\":\"Answer created successfully\"}"));
    }

    @Test
    void C_DET_01_service_RED_00() throws Exception {
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

        assertTrue(resultAnswer.getResponse().getStatus() == HttpStatus.NOT_FOUND.value());
        assertTrue(resultAnswer.getResponse().getContentAsString().equals("{\"message\":\"Question not found\"}"));
    }
}
