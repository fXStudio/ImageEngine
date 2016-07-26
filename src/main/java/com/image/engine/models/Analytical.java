package com.image.engine.models;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ETC稽查
 * 
 * @author FXStudio.Ajaxfan
 */
@Table(name = "st_analytical_tbl")
public class Analytical {
	@Id
	private String carImage;
	private String outDate;

	public String getCarImage() {
		return carImage;
	}

	public void setCarImage(String carImage) {
		this.carImage = carImage;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
}
