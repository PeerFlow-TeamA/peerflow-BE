package com.peer.missionpeerflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class AnswerCommentCreateDTO {
    @NotEmpty(message = "댓글을 작성한 작성자의 닉네임을 입력해주세요.")
    @Size(max = 20)
    private String nickname;
    @NotEmpty(message = "댓글을 작성한 작성자가 본인 확인용으로 입력한 비밀번호를 입력해주세요.")
    @Size(max = 30)
    private String password;
    @NotEmpty(message = "댓글의 내용을 입력해주세요.")
    private String content;
    @NotEmpty(message = "생성시간을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    AnswerCommentCreateDTO(String nickname, String password, String content, String createdAt){
        this.nickname = nickname;
        this.password = password;
        this.content = content;
        this.createdAt = LocalDateTime.parse(createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss"));
    }
}
