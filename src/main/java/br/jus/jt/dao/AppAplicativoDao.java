package br.jus.jt.dao;

import javax.persistence.TypedQuery;

import br.jus.jt.entity.AppAplicativo;


/**
 * Classe de acesso a dados de entidade AppUsuario.
 * 
 * @author trt05
 *
 */
public class AppAplicativoDao extends GenericDao<AppAplicativo> {
    

    /**
     * Retorna a quantidade de usuários registrados no sistema.
     * 
     * @return
     */
    public Long buscarTotalAplicativos() {
        
        String sql = "SELECT count(a) FROM AppAplicativo a ";
        TypedQuery<Long> query = getEntityManager().createQuery(sql, Long.class);
        return query.getSingleResult();
        
    }
}
