package com.peer.missionpeerflow.dto.request.questioncomment;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class QuestionCommentCreateDTO {
    @NotNull
    private String nickname;
    @NotNull
    private String password;
    @NotNull
    private String content;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private String createdAt;
}
