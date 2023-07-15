package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.response.QuestionDetailAnswerDTO;
import com.peer.missionpeerflow.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionDetailAnswerDTOMapper {
    QuestionDetailAnswerDTOMapper INSTANCE = Mappers.getMapper(QuestionDetailAnswerDTOMapper.class);

    @Mappings({
            @Mapping(target = "nickname", source = "answer.nickname"),
            @Mapping(target = "content" , source = "answer.content"),
            @Mapping(target = "createdAt", source = "answer.createdAt"),
            @Mapping(target = "updatedAt", source = "answer.updatedAt"),
            @Mapping(target = "type", constant = "answer"),
            @Mapping(target = "recommend", source = "answer.recommend"),
            @Mapping(target = "questionId", source = "answer.question.questionId"),
            @Mapping(target = "answerId", source = "answer.answerId"),
            @Mapping(target = "adopted", source = "answer.isAdopted"),

    })
    public QuestionDetailAnswerDTO toDTO(Answer answer);

    default List<QuestionDetailAnswerDTO> toDTOList(List<Answer> answers){
        return answers.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
