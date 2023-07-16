package com.peer.missionpeerflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AnswerDeleteDTO {
    @NotEmpty(message = "작성자의 비밀번호를 입력해주세요.")
    private String password;
}
