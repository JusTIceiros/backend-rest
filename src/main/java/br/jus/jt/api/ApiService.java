package br.jus.jt.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.manager.InteracaoManager;

@Path("/service")
public class ApiService {
	
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
    public Response interagir(InteracaoRequestDto interacaoRequestDto) {  
    	
		try {
			return Response.accepted(interacaoMgr.processarInteracao(interacaoRequestDto)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}    	
    }

}