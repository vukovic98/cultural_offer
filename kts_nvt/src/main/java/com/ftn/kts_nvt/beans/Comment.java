package com.ftn.kts_nvt.beans;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

	@ManyToOne
	@JoinColumn(name = "image_id", nullable = false)
	private Image image;

	@ManyToOne
	@JoinColumn(name = "commenter_id", nullable = false)
	private RegisteredUser commenter;

	public Comment() {
		super();
	}

	public Comment(String content, Image image, RegisteredUser commenter) {
		super();
		this.content = content;
		this.image = image;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public RegisteredUser getCommenter() {
		return commenter;
	}

	public void setCommenter(RegisteredUser commenter) {
		this.commenter = commenter;
	}

}
