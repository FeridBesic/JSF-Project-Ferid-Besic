package service;

import java.util.List;



import source.Telefon;

public class TelefonService extends Service{
	//private EntityManagerFactory emfactory;
	//private EntityManager entityManager;
	
	public TelefonService() {
		super();
		//emfactory = Persistence.createEntityManagerFactory( "Kontakti" );
	    //entityManager = emfactory.createEntityManager( );
	    entityManager.getTransaction( ).begin( );
	}
	
	public boolean CreateTelefon(String phoneNumber, String personId, int type) {
		this.checkTransactionState();
		  Telefon telefon = null;
		  telefon=this.FindTelefon(phoneNumber);
		  if(telefon!=null)
			  return false;
		  telefon = new Telefon(type,phoneNumber,personId );
	      entityManager.persist( telefon );
	      entityManager.getTransaction( ).commit( );
	      return true;
	      
}

	public void UpdateTelefonTip(String phoneNumber, int type) {
			 this.checkTransactionState();
			 Telefon telefon= entityManager.find( Telefon.class, phoneNumber);
		     telefon.setTip(type);
		     entityManager.getTransaction( ).commit( );
		      
		      //after update
}
	
	/*public void UpdateTelefonBroj(String oldPhoneNumber, String newPhoneNumber) {
		Telefon telefon = entityManager.find(Telefon.class, oldPhoneNumber);
		telefon.setBrojTelefona(newPhoneNumber);
	    entityManager.getTransaction( ).commit( );
	    
	}
	
		
	public void UpdateTelefonMultiple(String oldPhoneNumber, int newPhoneType, String newPhoneNumber) {
		Telefon telefon = entityManager.find(Telefon.class, oldPhoneNumber);
		if(newPhoneType!=0)
	    telefon.setTip(newPhoneType);
		if(newPhoneNumber!=null)
		telefon.setBrojTelefona(newPhoneNumber);
	    entityManager.getTransaction( ).commit( );
	}*/


	public Telefon FindTelefon(String phoneNumber) {
	   	  Telefon telefon = entityManager.find( Telefon.class, phoneNumber );
	   	  return telefon;
	   }

	public void DeleteTelefon(String phoneNumber) {
		this.checkTransactionState();
		Telefon telefon = entityManager.find( Telefon.class, phoneNumber );
	    entityManager.remove( telefon );
	    entityManager.getTransaction( ).commit( );
	     
	}
	
	public List<Telefon> FetchAllTelefon() {
		/*Query query = entityManager.createQuery("Select e from Telefon e");
	    List<Telefon> list = query.getResultList();
	    return list;*/
		return this.makeQuery("Select e from Telefon e");
	}
	
	public List<String> FetchAllTelefonskiBroj(){
		/*Query query=entityManager.createQuery("Select e.brojTelefona from Telefon e");
		List<String> list = query.getResultList();
		return list;*/
		return this.makeQuery("Select e.brojTelefona from Telefon e");
	}
	
	public List<Integer> FetchAllTelefonTip(){
		/*Query query=entityManager.createQuery("Select e.tip from Telefon e");
		List<Integer> list=query.getResultList();
		return list;*/
		return this.makeQuery("Select e.tip from Telefon e");
	}
	
	public List<String> FetchAllTelefonOsobaId(){
		/*Query query=entityManager.createQuery("Select e.osobaId from Telefon e ");
		List<String> list = query.getResultList();
		
		return list;*/
		return this.makeQuery("Select e.osobaId from Telefon e ");
	}
	
	public List<Telefon> FindTelefonByType(int type){
		
		/*Query query=entityManager.createQuery("Select e from Telefon e Where e.tip=" + type);
		List<Telefon> list = query.getResultList();
		
		return list;*/
		return this.makeQuery("Select e from Telefon e Where e.tip=" + type);
	}
	
	public List<Telefon> FindTelefonByOsobaId(String osobaId){
		/*Query query=entityManager.createQuery("Select e from Telefon e Where e.osobaId="+"'"+osobaId+"'");
		List<Telefon> list = query.getResultList();
		
		return list;*/
		return this.makeQuery("Select e from Telefon e Where e.osobaId="+"'"+osobaId+"'");
	}
	
	
	/*public void CloseConnection() {
		entityManager.close();
		emfactory.close();
	}*/
}
