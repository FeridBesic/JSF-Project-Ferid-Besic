package service;

import java.util.List;



import source.Osoba;

public class OsobaService extends Service{
	
	//private EntityManagerFactory emFactory;
	//private EntityManager entityManager;
	
	public OsobaService() {
		super();
		//emFactory = Persistence.createEntityManagerFactory( "Kontakti" );
	    //entityManager = emFactory.createEntityManager( );
	    entityManager.getTransaction( ).begin( );
	}
	
	public boolean CreateOsoba(String name,String surname,String id) {
		this.checkTransactionState();
	      Osoba osoba= null;
	      osoba=this.FindOsoba(id);
	      if(osoba!=null)
	    	  return false;
	      osoba= new Osoba(id,name,surname);
	      
	      entityManager.persist( osoba );
	      entityManager.getTransaction( ).commit( );
	      return true;
	     
}

		
	/*public void UpdateOsobaOsobaId(String id, String newId) {
		Osoba osoba = entityManager.find( Osoba.class, id );
	      osoba.setId(newId);
	      entityManager.getTransaction( ).commit( );
	}*/
	
	public void UpdateOsobaIme(String id, String newName) {
		this.checkTransactionState();
		Osoba osoba = entityManager.find( Osoba.class, id );
	      //before update
	      osoba.setIme( newName);
	      entityManager.getTransaction( ).commit( );
	}
	
	public void UpdateOsobaPrezime(String id, String newSurname) {
		this.checkTransactionState();
		Osoba osoba = entityManager.find( Osoba.class, id );
	      osoba.setPrezime( newSurname );
	      entityManager.getTransaction( ).commit( );
	}
	
	public void UpdateOsobaImeIPrezime(String id,String newName,String newSurname) {
		this.checkTransactionState();
		Osoba osoba = entityManager.find( Osoba.class, id );
	      if(newName!=null)
	      osoba.setIme(newName);
	      if(newSurname!=null)
	      osoba.setPrezime( newSurname );
	      entityManager.getTransaction( ).commit( );		
	}

	public Osoba FindOsoba(String id) {
	   	
	      Osoba osoba = entityManager.find( Osoba.class, id );

	      //System.out.println("osoba ID = " + osoba.getId() );
	      //System.out.println("osoba NAME = " + osoba.getIme());
	      //System.out.println("osoba SALARY = " + osoba.getPrezime());
	      return osoba;
	   }

	public void DeleteOsoba(String id) {
		  this.checkTransactionState();
	      Osoba osoba = entityManager.find( Osoba.class, id);
	      entityManager.remove( osoba );
	      entityManager.getTransaction( ).commit( );

}
	
	public List<String> FetchAllOsobaId(){
		/*Query query = entityManager.createQuery("Select e.id From Osoba e");
		List<String> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e.id From Osoba e");
		
	}
	
	public List<String> FetchAllOsobaIme(){
		/*Query query = entityManager.createQuery("Select e.ime From Osoba e");
		List<String> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e.ime From Osoba e");
	}
	
	public List<String> FetchAllOsobaPrezime(){
		/*Query query = entityManager.createQuery("Select e.prezime From Osoba e");
		List<String> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e.prezime From Osoba e");
	}
	
	public List<Osoba>FetchAllOsoba(){
		/*Query query = entityManager.createQuery("Select e From Osoba e");
		List<Osoba> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e From Osoba e");
	}
	
	public List<Osoba> FindOsobaByIme(String name){
		/*Query query = entityManager.createQuery("Select e From Osoba e Where e.ime="+"'"+name+"'");
		List<Osoba> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e From Osoba e Where e.ime="+"'"+name+"'");
	}
	
	public List<Osoba> FindOsobaByPrezime(String surname){
		/*Query query = entityManager.createQuery("Select e From Osoba e Where e.prezime="+"'"+surname+"'");
		List<Osoba> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e From Osoba e Where e.prezime="+"'"+surname+"'");
	}
	

	/*public void CloseConnection() {
	      entityManager.close( );
	      emFactory.close( );
	}*/
}
