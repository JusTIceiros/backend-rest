package br.jus.jt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificationRequestDto {
	
	@JsonProperty("verification_token")
	private String verificationToken;
	
	private String challenge;
	
	public String getVerificationToken() {
		return verificationToken;
	}
	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	
	
	

}
