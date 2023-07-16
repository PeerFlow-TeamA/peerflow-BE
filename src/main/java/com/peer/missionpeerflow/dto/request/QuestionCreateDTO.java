package com.peer.missionpeerflow.dto.request;

import com.peer.missionpeerflow.util.Category;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QuestionCreateDTO {
    @NotNull(message = "제목을 입력해주세요.")
    @Size(max=100, min = 1)
    private String title;
    @Size(max=20)
    private String nickname;
    @NotNull(message = "비밀번호를 입력해주세요.")
    @Size(max=30)
    private String password;
    @NotNull(message = "카테고리를 입력해주세요.")
    private Category category;
    @NotNull(message = "내용을 입력해주세요.")
    private String content;
    @NotNull(message = "생성날짜를 입력해주세요.")
    private String createdAt;
}