package com.peer.missionpeerflow.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "question_comment")
@NoArgsConstructor
@Getter
public class QuestionComment extends BaseTimeEntity {
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

	@NotNull
	@Column(name = "content")
	private String content;
}
