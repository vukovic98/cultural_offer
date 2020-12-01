package com.ftn.kts_nvt.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "image")
@Embeddable
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_ID")
	private Long id;

	@Column(name = "url", nullable = false)
	@NotBlank(message = "Image url cannot be empty.")
	private String url;

	public Image() {
		super();
	}

	public Image(Long id, @NotBlank(message = "Image url cannot be empty.") String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
