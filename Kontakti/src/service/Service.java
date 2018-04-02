package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import source.AdresaStanovanja;

public class Service {
	protected final static EntityManagerFactory emFactory=Persistence.createEntityManagerFactory( "Kontakti" );
	protected EntityManager entityManager;
	protected static int emCount=0;
	
	public Service() {
		//emFactory = Persistence.createEntityManagerFactory( "Kontakti" );
		entityManager = emFactory.createEntityManager();
		//entityManager.getTransaction( ).begin( );
		emCount++;
	}
	
	public void CloseConnection() {
		entityManager.close( );
		emCount--;
		System.out.println(emCount+" ems remaining and this one is "+entityManager.isOpen());
	    if(emCount==0) {
		emFactory.close( );
		System.out.println("CLOSING ENTITY MANAGER FACTORY AND FACTORY IS "+emFactory.isOpen()+" !!!");
	    }
	}
	
	protected void checkTransactionState() {
		if(!entityManager.getTransaction().isActive())
       	 entityManager.getTransaction().begin();
	}
	
	protected <T> List<T> makeQuery(String q){
		Query query=entityManager.createQuery(q);
		List<T> list=query.getResultList();
		return list;
	}
	
}
