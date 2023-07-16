package com.peer.missionpeerflow.dto.request;

import com.peer.missionpeerflow.util.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor()
public class QuestionModifyDTO {
    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(max=100)
    private String title;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(max=30)
    private String password;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

    @NotEmpty(message = "카테고리를 입력해주세요")
    private Category category;

    @NotNull(message = "수정 시간을 입력해주세요.")
    private String updatedAt;
}
