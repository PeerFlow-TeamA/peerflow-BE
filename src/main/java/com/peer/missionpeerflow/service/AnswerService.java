package com.peer.missionpeerflow.service;



import com.peer.missionpeerflow.dto.mapper.RequestAnswerDTOMapper;
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
    private final RequestAnswerDTOMapper requestAnswerDTOMapper;
    private final QuestionRepository questionRepository;

    public void create(AnswerCreateDTO answerCreateDTO) {
        Optional<Question> foundQuestion = this.questionRepository.findById(answerCreateDTO.getQuestionId());
        if (foundQuestion.isPresent() == false)
            throw new NotFoundException("Question not found");

        Answer saveEntiry = this.requestAnswerDTOMapper.toEntity(answerCreateDTO);
        this.answerRepository.save(saveEntiry);
    }

    public void modify(AnswerModifyDTO answerModifyDTO, Long answerId) {
        Optional<Answer> foundAnswer = this.answerRepository.findById(answerId);
        if (foundAnswer.isPresent() == false)
            throw new NotFoundException("Answer not found");
        if (answerModifyDTO.getPassword().equals(foundAnswer.get().getPassword()) == false)
            throw new NotFoundException("Password not matched");

        Answer saveEntity = this.requestAnswerDTOMapper.toEntity(answerModifyDTO, foundAnswer.get());
        this.answerRepository.save(saveEntity);
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
