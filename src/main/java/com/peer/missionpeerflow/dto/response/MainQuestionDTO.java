package com.peer.missionpeerflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

// A_MAI_00 에서 패스워드 미공개를 위한 DTO
@AllArgsConstructor
@Builder
@Getter
public class MainQuestionDTO {
    private Long id;
    private String nickname;
    private String title;
    private String category;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
