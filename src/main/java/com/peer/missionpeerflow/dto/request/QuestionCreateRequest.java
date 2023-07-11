package com.peer.missionpeerflow.dto.request;

import com.peer.missionpeerflow.util.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class QuestionCreateRequest {
    private String title;
    private String nickname;
    private String password;
    private Category category;
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
