package com.peer.missionpeerflow.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class AnswerModifyDTO {
    @NotEmpty(message = "작성자의 비밀번호를 입력해주세요.")
    @Size(max = 30)
    private String password;
    @NotEmpty(message = "답글의 내용을 입력해주세요.")
    private String content;
    @NotNull(message = "답글의 수정 시간을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime updatedAt;

    @Builder
    public AnswerModifyDTO(String password, String content, String updatedAt) {
        this.password = password;
        this.content = content;
        this.updatedAt = LocalDateTime.parse(updatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss"));
    }
}
