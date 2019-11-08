package br.jus.jt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayloadDto {
	
	private String type;
	private String text;
	// user

	public String getText() {
		return text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setText(String text) {
		this.text = text;
	}

}
