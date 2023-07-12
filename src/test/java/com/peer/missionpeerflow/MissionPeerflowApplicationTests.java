package com.peer.missionpeerflow;

import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MissionPeerflowApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testService() {
		QuestionCreateDTO questionRequest1 = new QuestionCreateDTO("asdfasdf 어려웡", "jun", "1234", "miniRt", "postman so hard", LocalDateTime.now());
		this.questionService.creat(questionRequest1);
	}
}
