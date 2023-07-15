package com.peer.missionpeerflow.dto.request.userrecord;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UserRecordDTO {
    @Nullable
    private String nickname;

    @NotNull(message = "본인 확인용으로 입력한 비밀번호를 입력해주세요.")
    private String password;
}