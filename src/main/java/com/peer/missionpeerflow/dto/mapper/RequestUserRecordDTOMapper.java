package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.request.answercomment.AnswerCommentCreateDTO;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.dto.request.userrecord.UserRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RequestUserRecordDTOMapper {
    RequestUserRecordDTOMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(RequestUserRecordDTOMapper.class);

    @Mappings({
            @Mapping(target = "nickname", source = "userRecordDTO.nickname"),
            @Mapping(target = "password", source = "userRecordDTO.password")
    })
    UserRecord toEntity(UserRecordDTO userRecordDTO);

    @Mappings({
            @Mapping(target = "nickname", source = "answerCommentCreateDTO.nickname"),
            @Mapping(target = "password", source = "answerCommentCreateDTO.password")
    })
    UserRecordDTO extractDTO(AnswerCommentCreateDTO answerCommentCreateDTO);
}
