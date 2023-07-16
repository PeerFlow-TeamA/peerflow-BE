package com.peer.missionpeerflow.entity;

import com.peer.missionpeerflow.util.Category;
import com.peer.missionpeerflow.util.CategoryAttributeConverter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Table(name = "question")
@Entity
@Setter
public class Question extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	@Convert(converter = CategoryAttributeConverter.class)
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(nullable = false)
	private Long recommend = 0L;

	@Column(nullable = false)
	private Long view = 0L;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answerList = new ArrayList<>();

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionComment> questionCommentList = new ArrayList<>();

	@OneToOne
	private UserRecord userRecord;
}
