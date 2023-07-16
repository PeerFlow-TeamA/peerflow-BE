package com.peer.missionpeerflow.service;


import com.peer.missionpeerflow.dto.mapper.QuestionDetailAnswerDTOMapper;
import com.peer.missionpeerflow.dto.mapper.QuestionDetailDTOMapper;
import com.peer.missionpeerflow.dto.mapper.RequestQuestionDTOMapper;
import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.dto.request.QuestionDeleteDTO;
import com.peer.missionpeerflow.dto.request.QuestionModifyDTO;
import com.peer.missionpeerflow.dto.response.QuestionDetailAnswerDTO;
import com.peer.missionpeerflow.dto.response.QuestionDetailDTO;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.repository.UserRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRecordRepository userRecordRepository;
    private final QuestionDetailDTOMapper questionDetailDTOMapper;
    private final QuestionDetailAnswerDTOMapper questionDetailAnswerDTOMapper;
    private final RequestQuestionDTOMapper requestQuestionDTOMapper;
    private final UserRecordService userRecordService;

    @Transactional
    public void creat(@NotNull QuestionCreateDTO questionCreateDTO){
        UserRecord userRecord = userRecordService.create(questionCreateDTO.getNickname(), questionCreateDTO.getPassword());
        Question question = this.requestQuestionDTOMapper.toEntity(questionCreateDTO, userRecord);
        this.questionRepository.save(question);
    }

    @Transactional
    public void modify(@NotNull QuestionModifyDTO questionModifyDTO, Long questionid){
        Optional<Question> question = this.questionRepository.findById(questionid);
        if (question.isPresent() == false)
            throw new NotFoundException("Question not found");
        if (question.get().getUserRecord().getPassword().equals(questionModifyDTO.getPassword()) == false)
            throw new ForbiddenException("Password is incorrect");

        question.get().setTitle(questionModifyDTO.getTitle());
        question.get().setContent(questionModifyDTO.getContent());
        question.get().setCategory(questionModifyDTO.getCategory());
        question.get().setUpdatedAt(LocalDateTime.parse(questionModifyDTO.getUpdatedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss")));
        this.questionRepository.save(question.get());
    }

    @Transactional
    public void delete(@NotNull QuestionDeleteDTO questionDeleteDTO, Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent() == false)
            throw new NotFoundException("Question not found");
        if (question.get().getUserRecord().getPassword().equals(questionDeleteDTO.getPassword()) == false)
            throw new ForbiddenException("Password is incorrect");

        this.questionRepository.deleteById(id);
    }

    @Transactional
    public QuestionDetailDTO getQuestionDetail(Long id){
        Optional<Question> optionalQuestion = this.questionRepository.findById(id);
        if (optionalQuestion.isPresent() == false)
            throw new NotFoundException("Question not found");
        Question question = optionalQuestion.get();
        question.setView(question.getView() + 1L);

        List<QuestionDetailAnswerDTO> questionDetailAnswerDTO = this.questionDetailAnswerDTOMapper.toDTOList(question.getAnswerList());
        QuestionDetailDTO questionDetailDTO = this.questionDetailDTOMapper.toDTO(question, questionDetailAnswerDTO);

        return questionDetailDTO;
    }

    @Transactional
    protected Question findQuestionByQuestionId(Long questionId) {
        Optional<Question> question = this.questionRepository.findById(questionId);
        if (question.isPresent() == false)
            throw new NotFoundException("Question not found");
        return question.get();
    }

    @Transactional
    public void recommend(Long questionId){
        Question question = findQuestionByQuestionId(questionId);
        question.setRecommend(question.getRecommend() + 1);
    }

    @Transactional
    public List<Answer> findAllAnswersByQuestionId(Long questionId){
        Question question = this.findQuestionByQuestionId(questionId);
        return question.getAnswerList();
    }
}