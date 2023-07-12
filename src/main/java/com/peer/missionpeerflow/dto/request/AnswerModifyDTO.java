package com.peer.missionpeerflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AnswerModifyDTO {
    @NotNull(message = "작성자의 닉네임을 입력해주세요.")
    private String nickname;
    @NotNull(message = "작성자의 비밀번호를 입력해주세요.")
    private String password;
    @NotNull(message = "답글의 내용을 입력해주세요.")
    private String content;
    @NotNull(message = "답글의 수정 시간을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime updatedAt;
}
