package com.peer.missionpeerflow.service;



import com.peer.missionpeerflow.dto.mapper.RequestAnswerDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.peer.missionpeerflow.repository.AnswerRepository;
import com.peer.missionpeerflow.dto.request.answer.AnswerCreateDTO;
import com.peer.missionpeerflow.dto.request.answer.AnswerModifyDTO;
import com.peer.missionpeerflow.dto.request.answer.AnswerDeleteDTO;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final RequestAnswerDTOMapper requestAnswerDTOMapper;


    @Transactional
    public void create(AnswerCreateDTO answerCreateDTO) {
        Question question = this.questionService.findQuestionByQuestionId(answerCreateDTO.getQuestionId());
        Answer saveEntiry = this.requestAnswerDTOMapper.toEntity(answerCreateDTO);
        this.answerRepository.save(saveEntiry);
    }

    @Transactional
    public void modify(AnswerModifyDTO answerModifyDTO, Long answerId) {
        Answer foundAnswer = this.findAnswerByAnswerId(answerId);
        if (answerModifyDTO.getPassword().equals(foundAnswer.getPassword()) == false)
            throw new NotFoundException("Password not matched");
        Answer saveEntity = this.requestAnswerDTOMapper.toEntity(answerModifyDTO, foundAnswer);
        this.answerRepository.save(saveEntity);
    }

    @Transactional
    public void delete(AnswerDeleteDTO answerDeleteDTO, Long answerId) {
        Answer foundAnswer = this.findAnswerByAnswerId(answerId);
        if (answerDeleteDTO.getPassword().equals(foundAnswer.getPassword()) == false)
            throw new NotFoundException("Password not matched");
        this.answerRepository.delete(foundAnswer);
    }

    @Transactional
    public Answer findAnswerByAnswerId(Long answerId) {
        Optional<Answer> answer = this.answerRepository.findById(answerId);
        if (answer.isPresent() == false)
            throw new NotFoundException("Answer not found");
        return answer.get();
    }

    protected void save(Answer answer) {
        this.answerRepository.save(answer);
    }
}
