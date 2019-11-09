package br.jus.jt.api;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.InteracaoResponseDto;
import br.jus.jt.manager.InteracaoManager;

@Path("/service")
public class ApiService {

	private static final String TWILIO_AUTH_TOKEN = "TWILIO_AUTH_TOKEN";
	public static String TWILIO_ACCOUNT_SID = "AC441a21d8dc6162b143080b01063e973b";

	@Inject
	private InteracaoManager interacaoMgr;

	@Context
	private HttpServletRequest httpRequest;

	@GET
	@Path("/healthcheck")
	@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
	public Response main() {
		return Response.ok("Ok").build();
	}

	@POST
	@Path("/interagir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
	public Response interagir(InteracaoRequestDto interacaoRequestDto) {

		try {
			return Response.ok(interacaoMgr.processarInteracao(interacaoRequestDto)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/whatsapp")
	public Response msg() {
		String mensagem = httpRequest.getParameter("Body");
		String from = httpRequest.getParameter("From");
		String to = httpRequest.getParameter("To");
		System.out.println("Mensagem recebida = " + mensagem);
		System.out.println("From: " + from);
		System.out.println("To: " + to);
		InteracaoRequestDto interacao = new InteracaoRequestDto();
		interacao.setEntradaUsuario(mensagem);
		interacao.setIdAtendimento(from);
		InteracaoResponseDto resposta = interacaoMgr.processarInteracao(interacao);
		System.out.println("Resposta " + resposta.getResultado());
		enviarMensagem(resposta, to);
		return Response.ok().build();
	}

	private void enviarMensagem(InteracaoResponseDto resposta, String to) {
		String token = System.getenv(TWILIO_AUTH_TOKEN);
		Twilio.init(TWILIO_ACCOUNT_SID, token);
        Message.creator(
                new com.twilio.type.PhoneNumber(resposta.getIdAtendimento()),
                new com.twilio.type.PhoneNumber(to),
                resposta.getResultado())
            .create();
	}
	
	@GET
    @Path("/obterMapaDeDenuncias")
    @Produces(MediaType.TEXT_HTML + "; charset=utf-8")
    public Response obterMapaDeDenuncias() {  
    	
		try {
			return Response.ok(interacaoMgr.obterMapaDeDenuncias()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}    	
    }

}