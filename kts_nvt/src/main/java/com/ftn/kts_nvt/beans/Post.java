package com.ftn.kts_nvt.beans;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "post_time", nullable = false)
	private Instant postTime;

	public Post() {
		super();
	}

	public Post(String title, String content, Instant postTime) {
		super();
		this.title = title;
		this.content = content;
		this.postTime = postTime;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getPostTime() {
		return postTime;
	}

	public void setPostTime(Instant postTime) {
		this.postTime = postTime;
	}

}
