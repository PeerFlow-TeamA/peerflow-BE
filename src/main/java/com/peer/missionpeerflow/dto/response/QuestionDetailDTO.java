package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.util.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Builder
@Getter
public class QuestionDetailDTO {
    @NotNull
    private String nickname;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<QuestionDetailAnswerDTO> answerList;
    @NotNull
    private String type;
    @NotNull
    private String title;
    @NotNull
    private Category category;
    @NotNull
    private Long recommend;
    @NotNull
    private Long view;
}
