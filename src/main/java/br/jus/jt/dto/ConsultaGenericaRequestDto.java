package br.jus.jt.dto;

public class ConsultaGenericaRequestDto {
	
	private String idConsulta;
	private ConsultaGenericaParametroDto parametros;
	public String getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(String idConsulta) {
		this.idConsulta = idConsulta;
	}
	public ConsultaGenericaParametroDto getParametros() {
		return parametros;
	}
	public void setParametros(ConsultaGenericaParametroDto parametros) {
		this.parametros = parametros;
	}
	
	

}
