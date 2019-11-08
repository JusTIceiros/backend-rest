package br.jus.jt.manager;

import br.jus.jt.dto.InteracaoRequestDto;
import br.jus.jt.dto.InteracaoResponseDto;

public class InteracaoManager {
	
	public InteracaoResponseDto processarInteracao(InteracaoRequestDto requisicaoUsuario) {
		
		InteracaoResponseDto result = new InteracaoResponseDto();
		
		result.setIdAtendimento(requisicaoUsuario.getIdAtendimento());
		
		// inicia o trabalho sujo...
		
		result.setResultado("Tudo ok até agora...");
		
		// fim trabalho sujo
		
		
		return result;
		
	}

}
