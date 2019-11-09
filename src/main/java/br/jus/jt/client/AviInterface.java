package br.jus.jt.client;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.jus.jt.dto.BuscaContatosDto;
import br.jus.jt.dto.ConsultaGenericaHackLocalidadesMapsResponse;
import br.jus.jt.dto.ConsultaGenericaHackResponse;
import br.jus.jt.dto.ConsultaGenericaRequestDto;

@Path("/assistente")
public interface AviInterface {

	@POST
    @Path("/consultaGenerica")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    ArrayList<ConsultaGenericaHackResponse> fazConsultaGenerica(ConsultaGenericaRequestDto dto);
	//Response fazConsultaGenerica(ConsultaGenericaRequestDto dto);
	
	@POST
    @Path("/consultaGenerica")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    ArrayList<ConsultaGenericaHackLocalidadesMapsResponse> fazConsultaGenericaHackLocalidadesMapsResponse(ConsultaGenericaRequestDto dto);

	@POST
    @Path("/consultaGenerica")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    ArrayList<BuscaContatosDto> fazConsultaGenericaBuscaContatos(ConsultaGenericaRequestDto dto);

}
