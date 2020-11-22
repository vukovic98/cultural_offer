package com.ftn.kts_nvt.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long commentId;

	@Column(name = "content", nullable = false)
	private String content;

	private List<String> images;

	@Column(name = "grade", nullable = false)
	private int grade;

	@ManyToOne
	@JoinColumn(name = "commenter_id", nullable = false)
	private RegisteredUser commenter;

	public Comment() {
		super();
	}

	public Comment(String content, List<String> images, int grade, RegisteredUser commenter) {
		super();
		this.content = content;
		this.images = images;
		this.grade = grade;
		this.commenter = commenter;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public RegisteredUser getCommenter() {
		return commenter;
	}

	public void setCommenter(RegisteredUser commenter) {
		this.commenter = commenter;
	}

}
