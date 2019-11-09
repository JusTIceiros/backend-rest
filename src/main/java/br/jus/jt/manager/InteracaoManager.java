package br.jus.jt.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.jt.api.ApiService;
import br.jus.jt.client.AviInterface;
import br.jus.jt.dto.BuscaContatosDto;
import br.jus.jt.dto.ConsultaGenericaHackLocalidadesMapsResponse;
import br.jus.jt.dto.ConsultaGenericaHackResponse;
import br.jus.jt.dto.ConsultaGenericaParametroDto;
import br.jus.jt.dto.ConsultaGenericaRequestDto;
import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.InteracaoResponseDto;
import br.jus.jt.dto.RetornoDialogoDto;

@ApplicationScoped
public class InteracaoManager {
	
	private HashMap<String, String> estadoAtendimento = new HashMap<String, String>();
	private ResteasyClient client = new ResteasyClientBuilder().build();
	final String path = "https://esb-hom.trt5.jus.br";
	private ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
	private AviInterface proxy = target.proxy(AviInterface.class);
	private final String M_API_KEY1 = "AIzaSyAwXgvxWrgfpq";
	private final String M_API_KEY2 = "DSXL0_iTarEY18N09y1RM";
	@Inject ApiService apiService;
	 
	
	public InteracaoResponseDto processarInteracao(InteracaoRequestDto requisicaoUsuario) {
		
		InteracaoResponseDto result = new InteracaoResponseDto();
		
		result.setIdAtendimento(requisicaoUsuario.getIdAtendimento());
		
		// inicia o trabalho sujo...
		ConsultaGenericaParametroDto paramDto = new ConsultaGenericaParametroDto();
		
		String idAtendimento = String.valueOf(requisicaoUsuario.getIdAtendimento());
		
		String proximaAcao = "/start";
		String textoDigitado = requisicaoUsuario.getEntradaUsuario();
		requisicaoUsuario.setTextoDigitado(textoDigitado);
		
		if (this.estadoAtendimento.containsKey(idAtendimento)) {
			proximaAcao = this.estadoAtendimento.get(idAtendimento);
		}
		
		requisicaoUsuario.setEntradaUsuario(proximaAcao);
		requisicaoUsuario.setProximaAcao(proximaAcao);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonString = "";
		
		try {
			jsonString = mapper.writeValueAsString(requisicaoUsuario);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Erro ao transformar mensagem de entrada do usuário (parse no json de entrada).",e1);
		}
		
		paramDto.setJsonStr(jsonString);
		
		ConsultaGenericaRequestDto requestDto = new ConsultaGenericaRequestDto();
		requestDto.setIdConsulta("hack");
		requestDto.setParametros(paramDto);		
				
		// POST
		ArrayList<ConsultaGenericaHackResponse> consultaResponse = proxy.fazConsultaGenerica(requestDto);
		
		String resultStr = "";
		
		if (consultaResponse.size()>0) {
			resultStr = consultaResponse.get(0).getF_call_message();
			
			try {
				if (resultStr != null) {
					RetornoDialogoDto retornoDialogo = mapper.readValue(resultStr, RetornoDialogoDto.class);
					this.estadoAtendimento.put(idAtendimento, retornoDialogo.getProxima_acao());
					resultStr = retornoDialogo.getTexto();
				} else {
					try {
						this.estadoAtendimento.remove(idAtendimento);
					} catch (Exception e) {
						e.printStackTrace();
					}
					resultStr = "Desculpe, ocorreu algum problema no meu sistema. Poderia tentar novamente?";
				}
				
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
	
	public String obterMapaDeDenuncias() {
		
		ConsultaGenericaParametroDto paramDto = new ConsultaGenericaParametroDto();
		
		paramDto.setJsonStr("{}");
		
		ConsultaGenericaRequestDto requestDto = new ConsultaGenericaRequestDto();
		requestDto.setIdConsulta("hackLocalidadesMaps");
		requestDto.setParametros(paramDto);		
				
		// POST
		ArrayList<ConsultaGenericaHackLocalidadesMapsResponse> consultaResponse = proxy.fazConsultaGenericaHackLocalidadesMapsResponse(requestDto);
		
		String resultStr = "https://maps.googleapis.com/maps/api/staticmap?center=sao%20marcos,salvador,bahia,brasil&zoom=12&size=700x700&maptype=roadmap";
		
		for (ConsultaGenericaHackLocalidadesMapsResponse consultaGenericaHackLocalidadesMapsResponse : consultaResponse) {
			resultStr += consultaGenericaHackLocalidadesMapsResponse.getLocalidade();
		}
		
		resultStr += "&key="+M_API_KEY1+M_API_KEY2;
		
		//return "<p><img border=\\\"0\\\" src=\\\""+resultStr+"\\\" alt=\\\"Pontos de denúncia feitos ao MP BA.\\\"></p>";
		return resultStr;
		
	}

	public String enviarPush(String mensagemPush) {
		ConsultaGenericaParametroDto paramDto = new ConsultaGenericaParametroDto();		
		paramDto.setJsonStr("{}");
		
		ConsultaGenericaRequestDto requestDto = new ConsultaGenericaRequestDto();
		requestDto.setIdConsulta("hackBuscaContatos");
		requestDto.setParametros(paramDto);		
				
		// POST
		ArrayList<BuscaContatosDto> consultaResponse = proxy.fazConsultaGenericaBuscaContatos(requestDto);
		
		String to = "whatsapp:+14155238886";
		
		for (BuscaContatosDto contato : consultaResponse) {
			
			try {
				InteracaoResponseDto resposta = new InteracaoResponseDto();
				resposta.setIdAtendimento(contato.getContato());
				resposta.setResultado(mensagemPush);
				this.apiService.enviarMensagem(resposta, to);
			} catch (Exception e) {				
				e.printStackTrace();				
			}
			
		}
		
		return "Push enviado com sucesso!";
	}

}

 