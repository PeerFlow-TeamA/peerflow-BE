package com.peer.missionpeerflow.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "answer_comment")
@Entity
public class AnswerComment extends BaseTimeEntity {
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

	@NotNull
	@Column(name = "content", nullable = false)
	private String content;
}
