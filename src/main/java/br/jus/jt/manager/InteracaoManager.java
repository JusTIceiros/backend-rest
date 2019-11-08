package br.jus.jt.manager;

import javax.inject.Inject;

import br.jus.jt.dao.AppAplicativoDao;
import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.InteracaoResponseDto;

public class InteracaoManager {
	
	@Inject
	private AppAplicativoDao aplicativoDao;
	
	public InteracaoResponseDto processarInteracao(InteracaoRequestDto requisicaoUsuario) {
		
		InteracaoResponseDto result = new InteracaoResponseDto();
		
		result.setIdAtendimento(requisicaoUsuario.getIdAtendimento());
		
		// inicia o trabalho sujo...
		Long teste = aplicativoDao.buscarTotalAplicativos();
		
		result.setResultado("Tudo ok até agora... resultado banco de dados: "+teste);
		
		// fim trabalho sujo
		
		
		return result;
		
	}

}
