package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.request.QuestionCreateDTO;
import com.peer.missionpeerflow.dto.request.QuestionModifyDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.UserRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface RequestQuestionDTOMapper {
    RequestQuestionDTOMapper INSTANCE = Mappers.getMapper(RequestQuestionDTOMapper.class);

    @Mappings({
            @Mapping(target = "title", source = "questionCreateDTO.title"),
            @Mapping(target = "nickname", source = "questionCreateDTO.nickname"),
            @Mapping(target = "password", source = "questionCreateDTO.password"),
            @Mapping(target = "category", source = "questionCreateDTO.category"),
            @Mapping(target = "content", source = "questionCreateDTO.content"),
            @Mapping(target = "createdAt", qualifiedByName = "stringToLocalDateTime"),
            @Mapping(target = "questionId", ignore = true ),
            @Mapping(target = "recommend", constant = "0L"),
            @Mapping(target = "view", constant = "0L"),
            @Mapping(target = "userRecord", source = "user"),
    })
    Question toEntity(QuestionCreateDTO questionCreateDTO, UserRecord user);

    @Named(value = "stringToLocalDateTime")
    static LocalDateTime stringToLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss"));
    }
}
