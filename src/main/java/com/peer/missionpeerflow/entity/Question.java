package com.peer.missionpeerflow.entity;

import com.peer.missionpeerflow.util.Category;
import com.peer.missionpeerflow.util.CategoryAttributeConverter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Table(name = "question")
@Entity
@Setter
public class Question extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;

	@NotNull
	@Column(name = "title")
	private String title;

	@NotNull
	@Column(name = "content")
	private String content;

	@NotNull
	@Convert(converter = CategoryAttributeConverter.class)
	private Category category;

	@NotNull
	@Column(name = "recommend")
	private Long recommend = 0L;

	@NotNull
	@Column(name = "view")
	private Long view = 0L;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answerList = new ArrayList<>();

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionComment> questionCommentList = new ArrayList<>();

	@OneToOne
	private UserRecord userRecord;
}
