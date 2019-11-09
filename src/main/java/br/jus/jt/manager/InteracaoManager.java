package br.jus.jt.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.imap.ResyncData;

import br.jus.jt.client.AviInterface;
import br.jus.jt.dto.ConsultaGenericaHackResponse;
import br.jus.jt.dto.ConsultaGenericaParametroDto;
import br.jus.jt.dto.ConsultaGenericaRequestDto;
import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.InteracaoResponseDto;
import br.jus.jt.dto.RetornoDialogoDto;

public class InteracaoManager {
	
	private HashMap<String, String> estadoAtendimento = new HashMap<String, String>();
	
	public InteracaoResponseDto processarInteracao(InteracaoRequestDto requisicaoUsuario) {
		
		InteracaoResponseDto result = new InteracaoResponseDto();
		
		result.setIdAtendimento(requisicaoUsuario.getIdAtendimento());
		
		// inicia o trabalho sujo...
		ConsultaGenericaParametroDto paramDto = new ConsultaGenericaParametroDto();
		
		String idAtendimento = String.valueOf(requisicaoUsuario.getIdAtendimento());
		
		String termoConsulta = "/start";
		
		if (this.estadoAtendimento.containsKey(idAtendimento)) {
			termoConsulta = this.estadoAtendimento.get(idAtendimento);
		}
		
		paramDto.setJsonStr(termoConsulta);
		
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
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				
				RetornoDialogoDto retornoDialogo = mapper.readValue(resultStr, RetornoDialogoDto.class);
				this.estadoAtendimento.put(idAtendimento, retornoDialogo.getProxima_acao());
				resultStr = retornoDialogo.getTexto();
				
			} catch (JsonParseException e) {
				e.printStackTrace();
				throw new RuntimeException("Erro ao recuperar diálogo do banco de dados (parse no json de retorno).",e);
			} catch (JsonMappingException e) {
				e.printStackTrace();
				throw new RuntimeException("Erro ao mapear dados do diálogo do banco de dados (mapping no json de retorno).",e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Erro de IO ao tratar dados do diálogo do banco de dados.",e);
			}
			
		}
		
		result.setResultado(resultStr);
		
		// fim trabalho sujo
		
		
		return result;
		
	}

}

