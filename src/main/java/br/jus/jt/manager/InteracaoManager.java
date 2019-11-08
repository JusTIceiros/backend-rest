package br.jus.jt.manager;

import java.util.ArrayList;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.jus.jt.client.AviInterface;
import br.jus.jt.dto.ConsultaGenericaHackResponse;
import br.jus.jt.dto.ConsultaGenericaParametroDto;
import br.jus.jt.dto.ConsultaGenericaRequestDto;
import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.InteracaoResponseDto;

public class InteracaoManager {
	
	public InteracaoResponseDto processarInteracao(InteracaoRequestDto requisicaoUsuario) {
		
		InteracaoResponseDto result = new InteracaoResponseDto();
		
		result.setIdAtendimento(requisicaoUsuario.getIdAtendimento());
		
		// inicia o trabalho sujo...
		ConsultaGenericaParametroDto paramDto = new ConsultaGenericaParametroDto();
		paramDto.setJsonStr(requisicaoUsuario.getEntradaUsuario());
		
		ConsultaGenericaRequestDto requestDto = new ConsultaGenericaRequestDto();
		requestDto.setIdConsulta("hack");
		requestDto.setParametros(paramDto);
		
		final String path = "https://esb-hom.trt5.jus.br";
				
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
		AviInterface proxy = target.proxy(AviInterface.class);
		 
		// POST
		ArrayList<ConsultaGenericaHackResponse> consultaResponse = proxy.fazConsultaGenerica(requestDto);
		
		String resultStr = "";
		
		if (consultaResponse.size()>0) {
			resultStr = consultaResponse.get(0).getF_call_message();
		}
		
		result.setResultado("Tudo ok até agora... resultado banco de dados: " + resultStr);
		
		// fim trabalho sujo
		
		
		return result;
		
	}

}

