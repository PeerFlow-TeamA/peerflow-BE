package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.dto.request.AnswerCreateDTO;
import com.peer.missionpeerflow.dto.request.AnswerModifyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RequestAnswerDTOMapper {
    RequestAnswerDTOMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(RequestAnswerDTOMapper.class);
    @Mappings({
            @Mapping(target = "answerId", ignore = true),
            @Mapping(target = "question.questionId", source = "answerCreateDTO.questionId"),
            @Mapping(target = "nickname", source = "answerCreateDTO.nickname"),
            @Mapping(target = "password", source = "answerCreateDTO.password"),
            @Mapping(target = "content", source = "answerCreateDTO.content"),
            @Mapping(target = "recommend", ignore = true),
            @Mapping(target = "isAdopted", constant = "false"),
            @Mapping(target = "createdAt", source = "answerCreateDTO.createdAt"),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "answerCommentList", ignore = true),
    })
    Answer toEntity(AnswerCreateDTO answerCreateDTO);

    @Mappings({
            @Mapping(target = "answerId", source = "originAnswer.answerId"),
            @Mapping(target = "question.questionId", source = "originAnswer.question.questionId"),
            @Mapping(target = "nickname", ignore = true),
            @Mapping(target = "password", source = "answerModifyDTO.password"),
            @Mapping(target = "content", source = "answerModifyDTO.content"),
            @Mapping(target = "recommend", source = "originAnswer.recommend"),
            @Mapping(target = "isAdopted", source = "originAnswer.isAdopted"),
            @Mapping(target = "createdAt", source = "originAnswer.createdAt"),
            @Mapping(target = "updatedAt", source = "answerModifyDTO.updatedAt"),
            @Mapping(target = "answerCommentList", source = "originAnswer.answerCommentList"),
    })
    Answer toEntity(AnswerModifyDTO answerModifyDTO, Answer originAnswer);
}
