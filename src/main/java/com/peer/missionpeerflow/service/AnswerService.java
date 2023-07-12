package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.peer.missionpeerflow.repository.AnswerRepository;
import com.peer.missionpeerflow.dto.request.AnswerCreateDTO;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public void create(AnswerCreateDTO answerCreateDTO) {
        Answer answer = new Answer();

        Optional<Question> question = this.questionRepository.findById(answerCreateDTO.getQuestionId());
        if (question.isPresent() == false)
            throw new NotFoundException("Question not found");

        answer.setQuestion(question.get());
        answer.setNickname(answerCreateDTO.getNickname());
        answer.setPassword(answerCreateDTO.getPassword());
        answer.setContent(answerCreateDTO.getContent());
        answer.setCreatedAt(answerCreateDTO.getCreatedAt());
        this.answerRepository.save(answer);
    }
}
