package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.request.AnswerCommentCreateDTO;
import com.peer.missionpeerflow.entity.AnswerComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RequestAnswerCommentDTOMapper {
    RequestQuestionDTOMapper INSTANCE = Mappers.getMapper(RequestQuestionDTOMapper.class);

    @Mappings({
            @Mapping(target = "nickname", source = "answerCommentCreateDTO.nickname"),
            @Mapping(target = "password", source = "answerCommentCreateDTO.password"),
            @Mapping(target = "content", source = "answerCommentCreateDTO.content"),
            @Mapping(target = "answer.answerId", source = "answerId"),
            @Mapping(target = "createdAt", source = "answerCommentCreateDTO.createdAt"),
            @Mapping(target = "updatedAt", ignore = true),
    })
    AnswerComment toEntity(AnswerCommentCreateDTO answerCommentCreateDTO, Long answerId);

}
