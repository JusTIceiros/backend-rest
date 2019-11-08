package br.jus.jt.manager;

import javax.inject.Inject;

import br.jus.jt.dao.DialogoDao;
import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.InteracaoResponseDto;

public class InteracaoManager {
	
	@Inject
	private DialogoDao dialogoDao;
	
	public InteracaoResponseDto processarInteracao(InteracaoRequestDto requisicaoUsuario) {
		
		InteracaoResponseDto result = new InteracaoResponseDto();
		
		result.setIdAtendimento(requisicaoUsuario.getIdAtendimento());
		
		// inicia o trabalho sujo...
		String resultStr = dialogoDao.callMessage(requisicaoUsuario.getEntradaUsuario());
		
		result.setResultado("Tudo ok até agora... resultado banco de dados: " + resultStr);
		
		// fim trabalho sujo
		
		
		return result;
		
	}

}
