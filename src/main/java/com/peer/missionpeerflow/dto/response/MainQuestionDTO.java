package com.peer.missionpeerflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

// A_MAI_00 에서 패스워드 미공개를 위한 DTO
@AllArgsConstructor
@Builder
@Getter
public class MainQuestionDTO {
    @NotNull
    private Long questionId;

    @NotNull
    private String title;

    @NotNull
    private Long answerCount;

    @NotNull
    private String category;

    @NotNull
    private Long recommend;

    @NotNull
    private Long view;

    @NotNull
    private String nickname;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private String content;
}