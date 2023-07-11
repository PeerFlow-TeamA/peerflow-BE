package com.peer.missionpeerflow.dto.mapper;

import com.peer.missionpeerflow.dto.response.MainQuestionDTO;
import com.peer.missionpeerflow.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface MainQuestionDTOMapper {
    MainQuestionDTOMapper INSTANCE = Mappers.getMapper(MainQuestionDTOMapper.class);

    @Mapping(target = "password", ignore = true)
    MainQuestionDTO toDTO(Question question);

    default Page<MainQuestionDTO> toMainQuestionDTOPage(Page<Question> questionList) {
        return questionList.map(this::toDTO);
    }
}
