package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.response.QuestionCommentDTO;
import com.peer.missionpeerflow.entity.QuestionComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionCommentDTOMapper {
    QuestionCommentDTOMapper INSTANCE = Mappers.getMapper(QuestionCommentDTOMapper.class);

    @Mappings({
            @Mapping(target = "nickname", source = "questionComment.nickname"),
            @Mapping(target = "content", source = "questionComment.content"),
            @Mapping(target = "createdAt", source = "questionComment.createdAt"),
            @Mapping(target = "updatedAt", source = "questionComment.updatedAt"),
            @Mapping(target = "type", constant = "question_comment"),
            @Mapping(target = "questionCommentId", source = "questionComment.questionCommentId"),
    })
    QuestionCommentDTO toDTO(QuestionComment questionComment);

    default Page<QuestionCommentDTO> toDtoPage(Page<QuestionComment> questionCommentPage){
        List<QuestionCommentDTO> questionCommentDTOList = questionCommentPage.getContent().stream().map(this::toDTO).collect(Collectors.toList());

        return new PageImpl<>(questionCommentDTOList, questionCommentPage.getPageable(), questionCommentPage.getTotalElements());
    }
}
