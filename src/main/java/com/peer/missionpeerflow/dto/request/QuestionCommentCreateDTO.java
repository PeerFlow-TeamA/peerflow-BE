package com.peer.missionpeerflow.dto.request;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class QuestionCommentCreateDTO {
    @NotEmpty(message = "닉네임을 입력해주세요.")
    @Size(max = 20)
    private String nickname;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private String createdAt;
}
