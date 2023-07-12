package com.peer.missionpeerflow.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class QuestionDeleteDTO {
    @NotNull(message = "비밀번호를 입력해주세요.")
    @Size(max=30)
    private String password;
}
