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

import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.InteracaoResponseDto;
import br.jus.jt.manager.InteracaoManager;

@Path("/service")
public class ApiService {
	
//	public static final String API_ENDPOINT = "https://api.messengerpeople.dev/messages";
//	public final static String VERIFICATION_TOKEN = "78326795-dc82-46ad-894a-f8d1bcd0";
//	public final static String CLIENT_ID = "a550a45c0b8a52c47fea96c4";
//	public final static String CLIENT_SECRET = "ef9decb86859a58d8f64f245622416a3";
//	public static String ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjhlZDYwZDAxM2IxN2U5NDRjNGFmZWQyY2Y5NmQxMDgyM2QyMGNjNjJmMGRkZTg3Y2ZmMzVkYWNiNDBlOWU5N2Q1MDhjYWExNDNiMTI0NDViIn0.eyJhdWQiOiJhNTUwYTQ1YzBiOGE1MmM0N2ZlYTk2YzQiLCJqdGkiOiI4ZWQ2MGQwMTNiMTdlOTQ0YzRhZmVkMmNmOTZkMTA4MjNkMjBjYzYyZjBkZGU4N2NmZjM1ZGFjYjQwZTllOTdkNTA4Y2FhMTQzYjEyNDQ1YiIsImlhdCI6MTU3MzI0NzQzMiwibmJmIjoxNTczMjQ3NDMyLCJleHAiOjE1NzU4Mzk0MzIsInN1YiI6IiIsInNjb3BlcyI6WyJtZXNzYWdlczpzZW5kIl0sImN1c3RvbWVyX2lkIjoxMDA0ODM0fQ.gaXwPR4HVs5IWi0vp_W25L5rB7R6GBoEdne4sLYZd5DR5lxUHqYptERoMWWm56X13mjCxwO-nbXNuxvgmXkwbl2K0_tiqK9yAdBFHoCI4riqs_RxyfdyBQhnHk0U_q3k2M24MP3K161J5Y3KxMtcZ7IiVaWXULpD2n_MzQPIX68Od4ulsWM56JFpnnulNFxjXqc0iMAQ7C9rTfqdFgLiwGZlssCJDz8qZ1UAEOFBSMEqRaqMcApcrKOBmgDe4diCL2qdxqc3vC0odATrNU0CrArGwFdIIcXPyYRW8q6ebIwWDcM43Pps1h8k0uNAP5WqL_BL9Fgi19ULcm7V_h-OVA";
	public static String TWILIO_ACCOUNT_SI = "AC441a21d8dc6162b143080b01063e973b";
	public static String TWILIO_AUTH_TOKEN = "6463b34ea6abfed0537cc3a27326a7bf";
	
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
    
//    @POST
//    @Path("/webhook")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
//    public Response verification(MessageRequestDto message) {
//    	System.out.println("## request ##");
//    	if (message != null && VERIFICATION_TOKEN.equals(message.getVerificationToken())) {
//    		System.out.println("## challenge ##");
//    		return challengeVerification(message);
//    	} else if (message.getPayload() != null){
//    		System.out.println("## payload ##");
//    		String text = message.getPayload().getText();
//    		System.out.println("## text payload: " + text);
//    		return responder(message);
//    	}
//    	
//    	return Response.status(Status.BAD_REQUEST).build();
//    }
//
//	private Response responder(MessageRequestDto message) {
//		MessageResponseDto resposta = new MessageResponseDto();
//		String identifier = message.getRecipient() + ":" + message.getSender();
//		resposta.setIdentifier(identifier);
//		PayloadDto payload = new PayloadDto();
//		payload.setText("Resposta do chatbot");
//		resposta.setPayload(payload);
////		Entity<MessageResponseDto> entity = Entity.json(resposta);
////		Response response = client.target(API_ENDPOINT)
////			.request()
////			.accept("application/json")
////			.post(entity);
//		return Response.ok(resposta).build();
//	}
//
//	private Response challengeVerification(MessageRequestDto verificacao) {
//		ChallengeResponseDto response = new ChallengeResponseDto(verificacao.getChallenge());
//		return Response.ok(response).build();
//	}
	
	@POST
	@Path("/whatsapp")
	public Response msg() {  
		String mensagem = httpRequest.getParameter("Body");
		String from = httpRequest.getParameter("From");
		System.out.println("Mensagem recebida = " + mensagem);
		InteracaoRequestDto interacao = new InteracaoRequestDto();
		interacao.setEntradaUsuario(mensagem);
		interacao.setIdAtendimento(from);
		InteracaoResponseDto resposta = interacaoMgr.processarInteracao(interacao);
		System.out.println("Resposta " + resposta.getResultado());
		return Response.ok().build();
	}
	
}