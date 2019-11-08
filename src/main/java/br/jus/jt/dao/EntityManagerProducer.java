//package br.jus.jt.dao;
//
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.inject.Disposes;
//import javax.enterprise.inject.Produces;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.FlushModeType;
//import javax.persistence.PersistenceUnit;
//
//public class EntityManagerProducer {
//	
//	@PersistenceUnit
//	private EntityManagerFactory factory;
//
//	@Produces
//	@RequestScoped
//	public EntityManager getEntityManager() throws Exception {
//		EntityManager entityManager = this.factory.createEntityManager();
//		entityManager.setFlushMode(FlushModeType.COMMIT);
//		return entityManager;
//	}
//
//	public void close(@Disposes EntityManager entityManager) throws Exception {
//		entityManager.close();
//	}
//}
