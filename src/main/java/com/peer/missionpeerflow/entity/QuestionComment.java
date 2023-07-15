package com.peer.missionpeerflow.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "question_comment")
@Entity
public class QuestionComment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionCommentId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	@NotNull
	@OneToOne
	@JoinColumn(name = "userRecord_id")
	private UserRecord userRecord;

	@Builder
	public QuestionComment(UserRecord userRecord, Question question, String content, LocalDateTime createdAt) {
		this.userRecord = userRecord;
		this.question = question;
		this.content = content;
		this.nickname = userRecord.getNickname();
		this.password = userRecord.getPassword();
		this.createdAt = createdAt;
	}
}
