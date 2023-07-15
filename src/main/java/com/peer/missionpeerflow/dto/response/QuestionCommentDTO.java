package com.peer.missionpeerflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class QuestionCommentDTO {
    @NotNull
    private String nickname;
    @NotNull
    private String content;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime updatedAt;
    @NotNull
    private String type;
    @NotNull
    private Long questionCommentId;
}
