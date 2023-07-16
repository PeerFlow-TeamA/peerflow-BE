package com.peer.missionpeerflow.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
public class UserRecordDTO {
    @NotEmpty(message = "닉네임을 입력해주세요.")
    @Size(max = 20)
    private String nickname;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Size(max = 20)
    private String password;
}