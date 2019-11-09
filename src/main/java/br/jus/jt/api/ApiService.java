package br.jus.jt.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.MessageDto;
import br.jus.jt.dto.PayloadDto;
import br.jus.jt.dto.ChallengeResponseDto;
import br.jus.jt.manager.InteracaoManager;

@Path("/service")
public class ApiService {
	
	public final static String VERIFICATION_TOKEN = "78326795-dc82-46ad-894a-f8d1bcd0";
	
	@Inject
	private InteracaoManager interacaoMgr;
	
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
    @Path("/webhook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public Response verification(MessageDto message) {
    	System.out.println("## request ##");
    	if (message != null && VERIFICATION_TOKEN.equals(message.getVerificationToken())) {
    		System.out.println("## challenge ##");
    		return challengeVerification(message);
    	} else if (message.getPayload() != null){
    		System.out.println("## payload ##");
    		String text = message.getPayload().getText();
    		System.out.println("## text payload: " + text);
    		return responder(message);
    	}
    	
    	return Response.status(Status.BAD_REQUEST).build();
    }

	private Response responder(MessageDto message) {
		MessageDto resposta = new MessageDto();
		resposta.setRecipient(message.getSender());
		resposta.setSender(message.getRecipient());
		PayloadDto payload = new PayloadDto();
		payload.setText("Resposta do chatbot");
		resposta.setPayload(payload );
		return Response.ok(resposta).build();
	}

	private Response challengeVerification(MessageDto verificacao) {
		ChallengeResponseDto response = new ChallengeResponseDto(verificacao.getChallenge());
		return Response.ok(response).build();
	}

}