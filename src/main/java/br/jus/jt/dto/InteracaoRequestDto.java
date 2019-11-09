package br.jus.jt.dto;

public class InteracaoRequestDto {
	
	private String entradaUsuario;
	private String idAtendimento;
	private String textoDigitado;
	private String proximaAcao;

	public String getTextoDigitado() {
		return textoDigitado;
	}

	public void setTextoDigitado(String textoDigitado) {
		this.textoDigitado = textoDigitado;
	}

	public String getProximaAcao() {
		return proximaAcao;
	}

	public void setProximaAcao(String proximaAcao) {
		this.proximaAcao = proximaAcao;
	}


	public String getEntradaUsuario() {
		return entradaUsuario;
	}

	public void setEntradaUsuario(String dadosEntrada) {
		this.entradaUsuario = dadosEntrada;
	}

	public String getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(String idAtendimento) {
		this.idAtendimento = idAtendimento;
	}
	
	

}
