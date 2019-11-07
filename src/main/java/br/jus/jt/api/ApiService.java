package br.jus.jt.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class ApiService {
	
    @GET
    public Response main() {
        return Response.ok("Ok").build();
    }

}
