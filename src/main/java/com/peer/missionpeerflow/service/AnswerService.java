package com.peer.missionpeerflow.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.repository.AnswerRepository;
import com.peer.missionpeerflow.dto.request.AnswerCreateDTO;
import com.peer.missionpeerflow.dto.request.AnswerModifyDTO;
import com.peer.missionpeerflow.dto.request.AnswerDeleteDTO;
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

    public void modify(AnswerModifyDTO answerModifyDTO, Long answerId) {
        Optional<Answer> answer = this.answerRepository.findById(answerId);
        if (answer.isPresent() == false)
            throw new NotFoundException("Answer not found");
        if (answerModifyDTO.getPassword().equals(answer.get().getPassword()) == false)
            throw new NotFoundException("Password not matched");

        Answer foundAnswer = answer.get();
        foundAnswer.setContent(answerModifyDTO.getContent());
        foundAnswer.setUpdatedAt(answerModifyDTO.getUpdatedAt());
        this.answerRepository.save(foundAnswer);
    }

    public void delete(AnswerDeleteDTO answerDeleteDTO, Long answerId) {
        Optional<Answer> answer = this.answerRepository.findById(answerId);
        if (answer.isPresent() == false)
            throw new NotFoundException("Answer not found");
        if (answerDeleteDTO.getPassword().equals(answer.get().getPassword()) == false)
            throw new NotFoundException("Password not matched");

        this.answerRepository.delete(answer.get());
    }
}
