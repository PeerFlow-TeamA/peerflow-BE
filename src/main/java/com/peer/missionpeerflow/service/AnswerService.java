package com.peer.missionpeerflow.service;



import com.peer.missionpeerflow.dto.mapper.RequestAnswerDTOMapper;
import com.peer.missionpeerflow.exception.DuplicatedAdoptionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.peer.missionpeerflow.repository.AnswerRepository;
import com.peer.missionpeerflow.dto.request.AnswerCreateDTO;
import com.peer.missionpeerflow.dto.request.AnswerModifyDTO;
import com.peer.missionpeerflow.dto.request.AnswerDeleteDTO;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Answer findAnswerByAnswerId(Long answerId) {
        Optional<Answer> answer = this.answerRepository.findById(answerId);
        if (answer.isPresent() == false)
            throw new NotFoundException("Answer not found");
        return answer.get();
    }

    @Transactional
    public void recommend(Long answerId){
        Answer answer = findAnswerByAnswerId(answerId);
        answer.setRecommend(answer.getRecommend() + 1);
    }

    @Transactional
    public void adopt(Long answerId){
        Answer answer = findAnswerByAnswerId(answerId);
        if (answer.getIsAdopted() == true)
            return ;
        List<Answer> answerList = this.questionService.findAllAnswersByQuestionId(answer.getQuestion().getQuestionId());
        if (answerList.stream().filter(a -> a.getIsAdopted() == true).findAny().isPresent() == true)
            throw new DuplicatedAdoptionException("another answer was already adopted");
        answer.setIsAdopted(true);
    }

    @Transactional
    public void save(Answer answer) {
        this.answerRepository.save(answer);
    }
}
