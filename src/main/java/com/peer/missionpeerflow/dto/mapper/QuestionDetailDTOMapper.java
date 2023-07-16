package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.response.QuestionDetailAnswerDTO;
import com.peer.missionpeerflow.dto.response.QuestionDetailDTO;
import com.peer.missionpeerflow.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionDetailDTOMapper {

    QuestionDetailDTOMapper INSTANCE = Mappers.getMapper(QuestionDetailDTOMapper.class);

    @Mappings({
            @Mapping(target = "nickname", source = "question.userRecord.nickname"),
            @Mapping(target = "content", source = "question.content"),
            @Mapping(target = "createAt", source = "question.createdAt"),
            @Mapping(target = "updateAt", source = "question.updatedAt"),
            @Mapping(target = "type", constant = "question"),
            @Mapping(target = "title", source = "question.title"),
            @Mapping(target = "category", source = "question.category"),
            @Mapping(target = "recommend", source = "question.recommend"),
            @Mapping(target = "view", source = "question.view"),
            @Mapping(target = "answerList", source = "answers" )
    })
    QuestionDetailDTO toDTO(Question question, List<QuestionDetailAnswerDTO> answers);
}
