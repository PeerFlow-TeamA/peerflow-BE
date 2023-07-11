package com.peer.missionpeerflow.dto.request;

import com.peer.missionpeerflow.util.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class QuestionCreateRequest {
    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(max = 200)
    private String title;
    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
    private Category category;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
    private LocalDateTime createdAt;

    public QuestionCreateRequest(String title, String nickname, String password, String category, String content, LocalDateTime createAt){
        this.title = title;
        this.nickname = nickname;
        this.password = password;
        this.category = Category.ofType(category);
        this.content = content;
        this.createdAt = createdAt;
    }
}
