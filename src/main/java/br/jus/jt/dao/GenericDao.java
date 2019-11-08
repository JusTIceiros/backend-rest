//package br.jus.jt.dao;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.Query;
//
//
///**
// * Classe abstrata para encapsular funcionalidades genéricas de acesso a dados.
// * 
// * @author trt05
// *
// * @param <E>
// */
//public class GenericDao<E> {
//    
//	@Inject
//    private EntityManager emPje;
//    
//    protected void setEntityManager(EntityManager entityManager) {
//        this.emPje = entityManager;
//    }
//    
//    protected EntityManager getEntityManager() {
//        return this.emPje;
//    }
//    
//    /**
//     * Persiste uma instância da entidade no banco de dados.
//     * 
//     * @param entidade A instância da entidade que deseja persistir.
//     */
//    public void incluir(E entidade) {
//        this.getEntityManager().persist(entidade);
//    }
//    
//    /**
//     * Exclui uma instância da entidade no banco de dados .
//     * 
//     * @param entidade A instância da entidade a ser excluída.
//     */
//    public void excluir(E entidade) {
//        this.getEntityManager().remove(entidade);
//    }
//    
//    /**
//     * Altera uma instância da entidade.
//     * 
//     * @param entidade A instância da entidade a ser alterada.
//     * @return A instancia da entidade anexada ao gerenciador de persistência.
//     */
//    public E alterar(E entidade) {
//        return this.getEntityManager().merge(entidade);
//    }
//    
//    /**
//     * Método que retorna resultado se for único ou null, se não existir.
//     * 
//     * Método ignora a exceção NoResultException, por não considerar erro da aplicação.
//     * 
//     * @param query A consulta que será executada.
//     * @return O único registro retornado na consulta, ou 'null' se não houver registro retornado.
//     */
//    @SuppressWarnings({ "unchecked", "hiding" })
//    public <E> E getSingleResultOrNull(Query query) {
//        try {
//            return (E) query.getSingleResult();
//        } catch (NoResultException noResultException) {
//            return null;
//        }
//    }
//    
//}
