package com.peer.missionpeerflow;

import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MissionPeerflowApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testService() {
	}
}
