package com.peer.missionpeerflow.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
public class AnswerCommentModifyDTO {

    @NotEmpty(message = "댓글을 작성한 작성자가 본인 확인용으로 입력한 비밀번호를 입력해주세요.")
    @Size(max = 30)
    private String password;
    @NotEmpty(message = "댓글의 내용을 입력해주세요.")
    private String content;
}
