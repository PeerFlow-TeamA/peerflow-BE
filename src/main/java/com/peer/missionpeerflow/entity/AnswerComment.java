package com.peer.missionpeerflow.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Table(name = "answer_comment")
@Entity
public class AnswerComment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerCommentId;

	@NotNull
	@OneToOne
	@JoinColumn(name = "writer_id")
	private UserRecord writer;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;

	@Builder
	public AnswerComment(UserRecord writer, Answer answer, String content) {
		this.writer = writer;
		this.answer = answer;
		this.content = content;
	}
}
