package com.ftn.kts_nvt.beans;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Embeddable
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_ID")
	private Long id;

	//@Column(name = "url", nullable = false)
	//@NotBlank(message = "Image url cannot be empty.")
	//private String url;

	@Column(name = "picByte", length = 5000)
	private byte[] picByte;


	public Image() {
		super();
	}

	public Image(Long id, byte[] picByte) {
		super();
		this.id = id;
		this.picByte = picByte;
	}

	public Image(byte[] picByte) {
		super();
		this.picByte = picByte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(picByte);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(picByte, other.picByte))
			return false;
		return true;
	}
	
	
}
