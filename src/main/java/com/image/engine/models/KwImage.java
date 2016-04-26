package com.image.engine.models;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "kw_image")
public class KwImage {
	@Id
	private String id;
	private byte[] image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
