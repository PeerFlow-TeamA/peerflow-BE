package com.peer.missionpeerflow.dto.request.answercomment;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class AnswerCommentDeleteDTO {
    @NotNull(message = "댓글을 작성한 작성자가 본인 확인용으로 입력한 비밀번호를 입력해주세요.")
    private String password;
}
