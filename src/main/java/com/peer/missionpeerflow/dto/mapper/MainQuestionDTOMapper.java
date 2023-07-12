package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.response.MainQuestionDTO;
import com.peer.missionpeerflow.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface MainQuestionDTOMapper {
    MainQuestionDTOMapper INSTANCE = Mappers.getMapper(MainQuestionDTOMapper.class);

    @Mappings({
            @Mapping(target = "questionId", source = "question.questionId"),
            @Mapping(target = "title", source = "question.title"),
            @Mapping(target = "answerCount", expression = "java(getAnswerCount(question))"),
            @Mapping(target = "category", source = "question.category"),
            @Mapping(target = "recommend", source = "question.recommend"),
            @Mapping(target = "view", source = "question.view"),
            @Mapping(target = "nickname", source = "question.userRecord.nickname"),
            @Mapping(target = "createdAt", source = "question.createdAt"),
            @Mapping(target = "content", source = "question.content"),
    })
    MainQuestionDTO toDTO(Question question);

    default Page<MainQuestionDTO> toMainQuestionDTOPage(Page<Question> questionList) {
        return questionList.map(this::toDTO);
    }

    default Long getAnswerCount(Question question) {
        return (long) question.getAnswerList().size();
    }
}
