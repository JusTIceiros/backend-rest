package br.jus.jt.dto;

public class ChallengeResponseDto {
	
	private String challenge;
	
	public ChallengeResponseDto(String challenge) {
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
