package com.peer.missionpeerflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AnswerCommentDTO {
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String content;
    @NotEmpty
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotEmpty
    private String type;
    @NotEmpty
    private Long answerCommentId;
}