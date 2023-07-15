package com.peer.missionpeerflow.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class AnswerCommentCreateDTO {
    @NotNull(message = "댓글을 달 답글의 id를 입력해주세요.")
    private Long answerId;
    @NotNull(message = "댓글을 작성한 작성자의 닉네임을 입력해주세요.")
    private String nickname;
    @NotNull(message = "댓글을 작성한 작성자가 본인 확인용으로 입력한 비밀번호를 입력해주세요.")
    private String password;
    @NotNull(message = "댓글의 내용을 입력해주세요.")
    private String content;
}
