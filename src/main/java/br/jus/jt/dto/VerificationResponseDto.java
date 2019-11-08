package br.jus.jt.dto;

public class VerificationResponseDto {
	
	private String challenge;
	
	public VerificationResponseDto(String challenge) {
		super();
		this.challenge = challenge;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	
}
