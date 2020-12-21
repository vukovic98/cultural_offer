package com.ftn.kts_nvt.beans;

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


	@ManyToOne
	@JoinColumn(name = "commenter_id", nullable = false)
	private RegisteredUser commenter;

	@Column(name = "approved", nullable = false)
	private boolean approved;

	public Comment() {
		super();
	}

	public Comment(String content, RegisteredUser commenter) {
		super();
		this.content = content;
		this.commenter = commenter;
		this.approved = false;
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

	public RegisteredUser getCommenter() {
		return commenter;
	}

	public void setCommenter(RegisteredUser commenter) {
		this.commenter = commenter;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content +", commenter="
				+ commenter + ", approved=" + approved + "]";
	}
	
}
