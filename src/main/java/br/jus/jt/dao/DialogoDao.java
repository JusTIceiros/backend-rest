//package br.jus.jt.dao;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import br.jus.jt.entity.Dialogo;
//
//
///**
// * Classe de acesso a dados de entidade AppUsuario.
// * 
// * @author trt05
// *
// */
//public class DialogoDao extends GenericDao<Dialogo> {
//	
//	EntityManager em = this.getEntityManager();
//    
//	public String callMessage( String jsonStr ) {
//	       
//        Query q = em.createNativeQuery(            
//                "SELECT hack.f_call_message(:p_jsonstr)"
//                );
//               
//        q.setParameter("p_jsonstr", jsonStr );
//        
//        String result = (String)this.getSingleResultOrNull(q);
//       
//        return result;       
//    }
//}
