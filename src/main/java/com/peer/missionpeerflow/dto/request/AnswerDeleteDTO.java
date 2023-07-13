package com.peer.missionpeerflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AnswerDeleteDTO {
    @NotNull(message = "작성자의 비밀번호를 입력해주세요.")
    private String password;
}
