package com.peer.missionpeerflow.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class QuestionCommentDeleteDTO {
    @NotNull(message = "비밀번호를 입력해주세요.")
    private String password;
}
