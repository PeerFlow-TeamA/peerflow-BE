package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.response.AnswerCommentDTO;
import com.peer.missionpeerflow.entity.AnswerComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerCommentDTOMapper {
    AnswerCommentDTOMapper INSTANCE = Mappers.getMapper(AnswerCommentDTOMapper.class);

    @Mappings({
            @Mapping(target = "nickname", source = "answerComment.nickname"),
            @Mapping(target = "content", source = "answerComment.content"),
            @Mapping(target = "createdAt", source = "answerComment.createdAt"),
            @Mapping(target = "updatedAt", source = "answerComment.updatedAt"),
            @Mapping(target = "type", constant = "answer_comment"),
            @Mapping(target = "answerCommentId", source = "answerComment.answerCommentId"),
    })
    AnswerCommentDTO toDTO(AnswerComment answerComment);

    default Page<AnswerCommentDTO> toDtoPage(Page<AnswerComment> answerCommentPage){
        List<AnswerCommentDTO> answerCommentDTOList = answerCommentPage.getContent().stream().map(this::toDTO).collect(Collectors.toList());

        return new PageImpl<>(answerCommentDTOList, answerCommentPage.getPageable(), answerCommentPage.getTotalElements());
    }
}
