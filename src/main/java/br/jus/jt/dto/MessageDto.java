package br.jus.jt.dto;

public class MessageDto {
	
	private String identifier;
	
	private PayloadDto payload;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public PayloadDto getPayload() {
		return payload;
	}

	public void setPayload(PayloadDto payload) {
		this.payload = payload;
	}

}
